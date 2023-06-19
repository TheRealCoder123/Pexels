package com.upnext.pexels.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.upnext.pexels.data.repository.IAuthRepository
import com.upnext.pexels.data.repository.ISearchRepository
import com.upnext.pexels.data.repository.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth, firestore: FirebaseFirestore) = IAuthRepository(auth, firestore)

    @Provides
    @Singleton
    fun provideUserRepository(firestore: FirebaseFirestore, auth: FirebaseAuth) = IUserRepository(firestore, auth)

    @Provides
    @Singleton
    fun provideSearchRepository(firestore: FirebaseFirestore) = ISearchRepository(firestore)

}