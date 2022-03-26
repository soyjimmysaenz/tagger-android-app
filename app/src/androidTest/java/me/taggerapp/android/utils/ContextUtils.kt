package me.taggerapp.android.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider

object ContextUtils {
    fun testAppContext() = ApplicationProvider.getApplicationContext<Context>()
}