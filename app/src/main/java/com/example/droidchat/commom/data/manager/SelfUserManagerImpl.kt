package com.example.droidchat.commom.data.manager

import android.content.Context
import com.example.droidchat.SelfUser
import com.example.droidchat.commom.data.datastore.selfUserStore
import com.example.droidchat.commom.data.di.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SelfUserManagerImpl(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SelfUserManager {

    private val selfUserStore = context.selfUserStore

    override val selfUserFlow: Flow<SelfUser>
        get() = selfUserStore.data

    override suspend fun saveSelfUserData(
        firstName: String,
        lastName: String,
        profilePictureUrl: String,
        username: String
    ) {
        withContext(ioDispatcher) {
            selfUserStore.updateData { selfUser ->
                selfUser.toBuilder().setUsername(username).setLastName(lastName)
                    .setFirstName(firstName).setProfilePictureUrl(profilePictureUrl).build()

            }
        }
    }

    override suspend fun clearSelfUserData() {
        selfUserStore.updateData {
            it.toBuilder().clearUsername().clearLastName().clearFirstName().clearProfilePictureUrl()
                .build()
        }
    }
}