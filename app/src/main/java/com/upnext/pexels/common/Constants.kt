package com.upnext.pexels.common

import android.content.Context
import android.content.res.Configuration
import com.google.firebase.auth.FirebaseAuth
import java.util.Locale
import kotlin.random.Random

object Constants {

    const val INTENT_PARAM_POSTS_TO_SERVICE = "INTENT_PARAM_POSTS_TO_SERVICE"

    const val PARAM_WHAT_SOCIAL_SHOULD_ADD = "what_social"
    const val PARAM_POST_ID = "post_id"

    fun isLoggedIn() : Boolean {
        val auth = FirebaseAuth.getInstance()
        return auth.currentUser != null
    }

    fun changeAppLanguage(languageCode: String, context: Context) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val resources = context.resources
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(locale)

        resources.updateConfiguration(configuration, resources.displayMetrics)
    }



}