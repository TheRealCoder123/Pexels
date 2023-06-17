package com.upnext.pexels.common

import java.util.Locale

enum class SocialUserPreferences {
    INSTAGRAM, TWITTER, WEBSITE
}

fun getSocialUserPreference(name: String) : SocialUserPreferences {
    return when (name.uppercase(Locale.getDefault())) {
        "INSTAGRAM" -> SocialUserPreferences.INSTAGRAM
        "TWITTER" -> SocialUserPreferences.TWITTER
        "WEBSITE" -> SocialUserPreferences.WEBSITE
        else -> throw IllegalArgumentException("Invalid social user preference: $name")
    }
}