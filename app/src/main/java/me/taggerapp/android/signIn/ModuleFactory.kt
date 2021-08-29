package me.taggerapp.android.signIn

import android.content.Context
import me.taggerapp.android.providers.PreferencesProvider
import me.taggerapp.android.providers.SharedPreferencesProviderImpl

object ModuleFactory {

    internal fun getSignUpController(context: Context): SignUpController {
        val preferencesProvider: PreferencesProvider = SharedPreferencesProviderImpl(context)
        val repository = UserSessionRepositoryImpl(preferencesProvider)

        return SignUpController(repository)
    }

    internal fun getStartAppController(context: Context): StartAppController {
        val preferencesProvider: PreferencesProvider = SharedPreferencesProviderImpl(context)
        val repository = UserSessionRepositoryImpl(preferencesProvider)

        return StartAppController(repository)
    }
}