package me.taggerapp.android.signIn

import me.taggerapp.android.providers.PreferencesProvider
import java.lang.Exception

class ValidateNewUserPin {

    companion object {
        private const val PIN_LENGTH = 6
    }

    operator fun invoke(userPinRequest: UserPinRequest): Boolean {
        return userPinRequest.pin?.length == PIN_LENGTH
    }
}

class SaveUserPin(private val preferencesProvider: PreferencesProvider) {

    companion object {
        private const val PIN_KEY = "user_pin"
    }

    operator fun invoke(userPinRequest: UserPinRequest): Boolean {
        val pin = userPinRequest.pin ?: return false
        return try {
            preferencesProvider.saveString(PIN_KEY, pin)
            true
        } catch (error: Exception) {
            //TODO: esto deberia ser mapeado y propagado
            false
        }
    }
}