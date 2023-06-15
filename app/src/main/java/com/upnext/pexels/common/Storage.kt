package com.upnext.pexels.common

sealed class Storage(val folder: String){
    object Users : Storage("users")
    object Posts : Storage("posts")
}