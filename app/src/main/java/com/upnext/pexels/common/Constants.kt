package com.upnext.pexels.common

import com.google.firebase.auth.FirebaseAuth
import kotlin.random.Random

object Constants {

    const val INTENT_PARAM_POSTS_TO_SERVICE = "INTENT_PARAM_POSTS_TO_SERVICE"

    fun isLoggedIn() : Boolean {
        val auth = FirebaseAuth.getInstance()
        return auth.currentUser != null
    }



}