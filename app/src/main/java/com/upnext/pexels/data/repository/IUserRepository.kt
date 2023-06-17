package com.upnext.pexels.data.repository

import android.net.Uri
import androidx.compose.runtime.rememberCoroutineScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.upnext.pexels.common.FirestoreCollections
import com.upnext.pexels.common.Resource
import com.upnext.pexels.data.remote.Post
import com.upnext.pexels.data.remote.User
import com.upnext.pexels.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class IUserRepository(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
) : UserRepository {

    override suspend fun getUserData(userId: String): Flow<Resource<User>> = callbackFlow {
        trySend(Resource.Loading()).isSuccess

        val listener = firestore.collection(FirestoreCollections.Users.collection)
            .document(userId)
            .addSnapshotListener { value, error ->
                value?.let {
                    val user = it.toObject(User::class.java)
                    trySend(Resource.Success(user)).isSuccess
                }

                error?.let {
                    trySend(Resource.Error(null, it.localizedMessage ?: "Unknown error")).isSuccess
                }
            }

        awaitClose { listener.remove() }
    }

    override suspend fun getLoggedInUserId(): String {
        return auth.currentUser?.uid!!
    }

    override suspend fun getUserPosts(userId: String): List<Post> {
        return firestore.collection(FirestoreCollections.Posts.collection)
            .whereEqualTo("userId", userId)
            .get().await().toObjects(Post::class.java)
    }

    override suspend fun updateUserProfile(userHashMap: HashMap<String, Any>) {
        firestore.collection(FirestoreCollections.Users.collection)
            .document(getLoggedInUserId()).update(userHashMap).await()
    }


}