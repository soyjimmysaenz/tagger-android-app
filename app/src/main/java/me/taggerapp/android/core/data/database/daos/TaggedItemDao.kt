package me.taggerapp.android.core.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.taggerapp.android.core.data.database.entities.TaggedItemEntity

@Dao
interface TaggedItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TaggedItemEntity)

    @Insert
    fun insertSync(entities: List<TaggedItemEntity>)

    @Query("SELECT * FROM tagged_items")
    suspend fun getAll(): List<TaggedItemEntity>
}