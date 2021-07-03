package me.taggerapp.android.signIn

import me.taggerapp.android.providers.PreferencesProvider
import java.lang.Exception

private object Constants {
    const val PIN_KEY = "user_pin"
}

class ValidateNewUserPin {

    companion object {
        private const val PIN_LENGTH = 6
    }

    operator fun invoke(userPinRequest: UserPinRequest): Boolean {
        //TODO: implementar tests (local)
        return userPinRequest.pin?.length == PIN_LENGTH
    }
}

class SaveUserPin(private val preferencesProvider: PreferencesProvider) {

    operator fun invoke(userPinRequest: UserPinRequest): Boolean {
        //TODO: implementar tests (instrumented y local)
        val pin = userPinRequest.pin ?: return false
        return try {
            //TODO: usar repository
            preferencesProvider.saveString(Constants.PIN_KEY, pin)
            true
        } catch (error: Exception) {
            //TODO: esto deberia ser mapeado y propagado
            false
        }
    }
}

class GetUserPin(private val preferencesProvider: PreferencesProvider) {

    operator fun invoke(): String? {
        return try {
            preferencesProvider.getString(Constants.PIN_KEY)
        } catch (error: Exception) {
            //TODO: esto deberia ser mapeado y propagado
            null
        }
    }
}