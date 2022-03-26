package me.taggerapp.android.core.data.database

import androidx.room.Room
import me.taggerapp.android.utils.ContextUtils.testAppContext

object TestAppDatabaseUtils {

    fun buildTestDatabase(): DefaultAppDatabase = Room
        .inMemoryDatabaseBuilder(testAppContext(), DefaultAppDatabase::class.java)
        .build()
}