package me.taggerapp.android.taggedItems

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

class DatabaseTaggedItemsDataSource(
    private val taggedItemDao: TaggedItemDao
) {
    fun getAll(): List<TaggedItemEntity> {
        return taggedItemDao.getAll()
    }

    fun insert(entity: TaggedItemEntity) {
        taggedItemDao.insert(entity)
    }
}

@Dao
interface TaggedItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: TaggedItemEntity)

    @Query("SELECT * FROM tagged_items")
    fun getAll(): List<TaggedItemEntity>
}