package com.upnext.pexels.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.upnext.pexels.common.FirestoreCollections
import com.upnext.pexels.data.remote.LoginParams
import com.upnext.pexels.data.remote.RegisterParams
import com.upnext.pexels.data.remote.User
import com.upnext.pexels.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await

class IAuthRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {
    override suspend fun register(registerParams: RegisterParams): Boolean {

        return try {

            val loggedInUser = auth.createUserWithEmailAndPassword(registerParams.email, registerParams.password)
                .await()

            val userTag = registerParams.email.split("@")[0]

            val user = User(
                loggedInUser.user?.uid!!,
                registerParams.firstName,
                registerParams.lastname,
                registerParams.email,
                "",
                0,0,
                "@${userTag}"
            )

            firestore.collection(FirestoreCollections.Users.collection)
                .document(loggedInUser.user?.uid!!).set(user).await()

            auth.signOut()

            true
        }catch (e: Exception){
            false
        }

    }

    override suspend fun login(loginParams: LoginParams): Boolean {
        return try {
            auth.signInWithEmailAndPassword(loginParams.email, loginParams.password)
                .await()
            true
        }catch (e: Exception){
            false
        }
    }


}