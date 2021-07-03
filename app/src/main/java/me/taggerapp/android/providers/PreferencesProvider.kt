package me.taggerapp.android.providers

import android.content.Context
import android.content.SharedPreferences
import me.taggerapp.android.BuildConfig

interface PreferencesProvider {
    fun saveString(key: String, value: String)
}

class SharedPreferencesProviderImpl(context: Context): PreferencesProvider {

    companion object {
        private const val PREF_NAME = "${BuildConfig.APPLICATION_ID}.root_prefs"
    }

    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val prefsEditor: SharedPreferences.Editor
    get() = prefs.edit()

    override fun saveString(key: String, value: String) {
        prefsEditor.putString(key, value).apply()
    }
}