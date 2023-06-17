package com.upnext.pexels.presentation.service

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.common.reflect.TypeToken
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import com.upnext.pexels.R
import com.upnext.pexels.common.Constants
import com.upnext.pexels.common.FirestoreCollections
import com.upnext.pexels.common.Storage
import com.upnext.pexels.data.remote.Post
import com.upnext.pexels.data.remote.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@AndroidEntryPoint
class UploadPostService : Service() {

    private lateinit var notificationManager : NotificationManagerCompat
    private lateinit var  mNotifyManager : NotificationManager
    private lateinit var  mBuilder : NotificationCompat.Builder

    @Inject
    lateinit var storage: FirebaseStorage


    @Inject
    lateinit var auth: FirebaseAuth

    @Inject
    lateinit var firestore: FirebaseFirestore

    companion object {
        const val CHANNEL_ID = "9918"
        const val CHANNEL_NAME = "Post Uploading"
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        mNotifyManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
        notificationManager = NotificationManagerCompat.from(this)

        val stringPosts = intent?.getStringExtra(Constants.INTENT_PARAM_POSTS_TO_SERVICE)
        val posts = Gson().fromJson<List<Post>>(stringPosts, object : TypeToken<List<Post>>() {}.type)

        Log.e("posts", "$posts")
        Log.e("str posts", "$stringPosts")

        createNotificationChannel()

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_drive_folder_upload_24)
            .setContentTitle("Uploading Posts")
            .setContentText("Upload in progress")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setProgress(0, 0, false)
            .setOngoing(true)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notificationManager.notify(1, notificationBuilder.build())
        }
        notificationManager.notify(1, notificationBuilder.build())

        val totalPosts = posts.size
        val coroutineScope = CoroutineScope(Dispatchers.IO)

        for (post in posts) {
            val index = posts.indexOf(post)
            val progressText = "Uploading video ${index + 1} of $totalPosts"
            val progress = ((index + 1) * 100) / totalPosts

            notificationBuilder.setProgress(100, progress, false)
            notificationBuilder.setContentText(progressText)

            notificationManager.notify(1, notificationBuilder.build())

            val postId = firestore.collection(FirestoreCollections.Posts.collection).document().id

            coroutineScope.launch {
                try {

                    val uploadTask = storage.reference.child(Storage.Users.folder)
                        .child("/${auth.uid}")
                        .child(Storage.Posts.folder)
                        .child("/${postId}")
                        .putFile(Uri.parse(post.postUrl)).await()

                    val postUrl = uploadTask.metadata!!.reference!!.downloadUrl.await()

                    val newPost = Post(
                        postId,
                        postUrl.toString(),
                        post.tags,
                        post.location,
                        post.views,
                        post.downloads,
                        post.category,
                        auth.uid!!
                    )

                    firestore.collection(FirestoreCollections.Posts.collection)
                        .document(postId)
                        .set(newPost, SetOptions.merge()).await()

                }catch (e: Exception){
                    e.printStackTrace()
                }
            }

            if(posts.last() == post){
                notificationBuilder.setContentText("Upload has finished")
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setProgress(0, 0, false)
                    .setOngoing(false)
                notificationManager.notify(1, notificationBuilder.build())
            }

        }

        return START_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            mNotifyManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mNotifyManager.createNotificationChannel(channel)
        }
    }


}