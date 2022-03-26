package me.taggerapp.android.core.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.taggerapp.android.core.data.database.daos.TaggedItemDao
import me.taggerapp.android.core.data.database.entities.TaggedItemEntity

interface AppDatabase {
    fun taggedItemDao(): TaggedItemDao
}

@Database(
    entities = [
        TaggedItemEntity::class
    ],
    version = 1,
)
abstract class DefaultAppDatabase : RoomDatabase(), AppDatabase {

    abstract override fun taggedItemDao(): TaggedItemDao

    companion object {
        private const val DB_NAME = "tagger_main_db"

        @Volatile
        private var instance: DefaultAppDatabase? = null

        /*
        refs:
        https://github.com/google-developer-training/android-kotlin-fundamentals-apps/blob/c685d8f3f19983660ac7173b16f1e9cb76acb7d7/RecyclerViewGridLayout/app/src/main/java/com/example/android/trackmysleepquality/database/SleepDatabase.kt
        https://github.com/android/sunflower/blob/0ee1afff0dc02f6ba53a8de79994b6ffd29c3106/app/src/main/java/com/google/samples/apps/sunflower/data/AppDatabase.kt
         */
        fun getInstance(context: Context): DefaultAppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): DefaultAppDatabase = Room
            .databaseBuilder(
                context.applicationContext,
                DefaultAppDatabase::class.java,
                DB_NAME
            )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}