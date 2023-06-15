package com.upnext.pexels.common

sealed class FirestoreCollections (val collection: String){
    object Users : FirestoreCollections(collection = "users")
    object Posts : FirestoreCollections(collection = "posts")
}
