package me.taggerapp.android.signIn

import me.taggerapp.android.providers.PreferencesProvider

interface UserSessionRepository {
    fun getUserPin(): String?
    fun savePin(userPinRequest: UserPinRequest)

    companion object {
        const val PIN_KEY = "user_pin"
    }
}

class UserSessionRepositoryImpl(
    private val preferencesProvider: PreferencesProvider
) : UserSessionRepository {

    override fun savePin(userPinRequest: UserPinRequest) {
        val pin = userPinRequest.pin ?: throw IllegalArgumentException("PIN inválido")
        return preferencesProvider.saveString(UserSessionRepository.PIN_KEY, pin)
    }

    override fun getUserPin(): String? {
        return preferencesProvider.getString(UserSessionRepository.PIN_KEY)
    }
}