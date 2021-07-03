package me.taggerapp.android.signIn

import android.content.Context
import me.taggerapp.android.providers.PreferencesProvider
import me.taggerapp.android.providers.SharedPreferencesProviderImpl

object ModuleFactory {

    internal fun getSignUpController(context: Context): SignUpController {
        val validateUserPin = ValidateNewUserPin()
        val preferencesProvider: PreferencesProvider = SharedPreferencesProviderImpl(context)
        val saveUserPin = SaveUserPin(preferencesProvider)

        return SignUpController(validateUserPin, saveUserPin)
    }
}