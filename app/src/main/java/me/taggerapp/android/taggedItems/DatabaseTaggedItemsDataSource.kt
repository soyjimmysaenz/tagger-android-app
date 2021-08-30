package me.taggerapp.android.taggedItems

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

class DatabaseTaggedItemsDataSource(
    private val taggedItemDao: TaggedItemDao
) {
    suspend fun getAll(): List<TaggedItemEntity> {
        //agregado para afectos de demostracion
        //TODO: eliminar
        kotlinx.coroutines.delay(600)
        return taggedItemDao.getAll()
    }

    suspend fun insert(entity: TaggedItemEntity) {
        //agregado para afectos de demostracion
        //TODO: eliminar
        kotlinx.coroutines.delay(500)
        taggedItemDao.insert(entity)
    }
}

@Dao
interface TaggedItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TaggedItemEntity)

    @Query("SELECT * FROM tagged_items")
    suspend fun getAll(): List<TaggedItemEntity>
}