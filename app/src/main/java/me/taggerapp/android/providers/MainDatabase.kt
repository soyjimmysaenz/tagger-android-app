package me.taggerapp.android.providers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.taggerapp.android.taggedItems.TaggedItemDao
import me.taggerapp.android.taggedItems.TaggedItemEntity

interface MainDatabaseProvider {
    fun taggedItemDao(): TaggedItemDao
}

@Database(entities = [TaggedItemEntity::class], version = 1)
abstract class MainDatabase : RoomDatabase(), MainDatabaseProvider {
    abstract override fun taggedItemDao(): TaggedItemDao

    companion object {
        @Volatile
        private var INSTANCE: MainDatabase? = null

        //ref: https://github.com/google-developer-training/android-kotlin-fundamentals-apps/blob/c685d8f3f19983660ac7173b16f1e9cb76acb7d7/RecyclerViewGridLayout/app/src/main/java/com/example/android/trackmysleepquality/database/SleepDatabase.kt
        fun getInstance(context: Context): MainDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainDatabase::class.java,
                        "tagger_main_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}