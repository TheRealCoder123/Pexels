package com.upnext.pexels.presentation.upload_screen.other

import android.content.ContentResolver
import android.content.ContentResolver.SCHEME_CONTENT
import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import com.google.firebase.storage.StorageReference
import com.upnext.pexels.common.Storage
import java.util.Locale

object Media {
    fun isVideoUri(context: Context, uri: Uri): Boolean {
        val mimeType = getMimeType(context.contentResolver, uri)
        return mimeType?.startsWith("video/") == true
    }

    fun isImageUri(context: Context, uri: Uri): Boolean {
        val mimeType = getMimeType(context.contentResolver, uri)
        return mimeType?.startsWith("image/") == true
    }


    private fun getMimeType(contentResolver: ContentResolver, uri: Uri): String? {
        val scheme = uri.scheme
        return if (scheme == SCHEME_CONTENT) {
            contentResolver.getType(uri)
        } else {
            val fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString())
            MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.lowercase(Locale.getDefault()))
        }
    }
}