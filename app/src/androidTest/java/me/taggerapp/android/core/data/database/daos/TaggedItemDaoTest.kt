package me.taggerapp.android.core.data.database.daos

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import me.taggerapp.android.core.data.database.DefaultAppDatabase
import me.taggerapp.android.core.data.database.entities.TaggedItemEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
class TaggedItemDaoTest {

    private lateinit var taggedItemDao: TaggedItemDao
    private lateinit var database: DefaultAppDatabase

    @Before
    fun setup() {
        setupDatabase()
    }

    private fun setupDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room
            .inMemoryDatabaseBuilder(context, DefaultAppDatabase::class.java)
            .build()
        taggedItemDao = database.taggedItemDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun getAllReturnsItemsSuccessfully_when_taggedItemTableHasItems() = runBlocking {
        //arrange
        val entitiesToSave = listOf(
            buildTaggedItem(uuid()),
            buildTaggedItem(uuid())
        )
        taggedItemDao.insertSync(entitiesToSave)

        //act
        val actualEntities = taggedItemDao.getAll()

        //assert
        assertThat(actualEntities).isNotEmpty()
        assertThat(actualEntities).hasSize(entitiesToSave.size)
    }

    private fun buildTaggedItem(id: String): TaggedItemEntity {
        return TaggedItemEntity(
            id = id,
            title = "Title $id",
            description = "Description $id",
            rating = Random.nextInt(1, 5),
            imagePath = null,
            createdAt = now(),
        )
    }

    private fun uuid() = UUID.randomUUID().toString()

    private fun now() = System.currentTimeMillis()
}