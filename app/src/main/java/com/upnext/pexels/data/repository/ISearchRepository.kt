package com.upnext.pexels.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.upnext.pexels.common.Category
import com.upnext.pexels.common.FirestoreCollections
import com.upnext.pexels.data.remote.Post
import com.upnext.pexels.domain.repository.SearchRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ISearchRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) : SearchRepository {
    override suspend fun getByCategory(category: Category): List<Post> {
        return firestore.collection(FirestoreCollections.Posts.collection)
            .whereArrayContains("category", category).limit(20)
            .get().await().toObjects(Post::class.java)
    }

    override suspend fun getByTag(tag: String): List<Post> {
        return firestore.collection(FirestoreCollections.Posts.collection)
            .whereArrayContains("tags", tag).get().await()
            .toObjects(Post::class.java)
    }
}