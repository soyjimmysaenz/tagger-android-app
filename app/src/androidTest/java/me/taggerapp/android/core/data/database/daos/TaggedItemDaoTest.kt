package me.taggerapp.android.core.data.database.daos

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import me.taggerapp.android.core.data.database.DefaultAppDatabase
import me.taggerapp.android.core.data.database.TestAppDatabaseUtils.buildTestDatabase
import me.taggerapp.android.core.data.database.daos.TaggedItemDaoTest.Fixtures.taggedItem
import me.taggerapp.android.core.data.database.entities.TaggedItemEntity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaggedItemDaoTest {

    private lateinit var database: DefaultAppDatabase
    private lateinit var taggedItemDao: TaggedItemDao

    @Test
    fun getAllSuccessfully_when_databaseHasItems() = runBlocking {
        //arrange
        database = buildTestDatabase()
        taggedItemDao = database.taggedItemDao()
        val expectedItemsToInsert = listOf(
            taggedItem("1"),
            taggedItem("2")
        )
        taggedItemDao.insertSync(expectedItemsToInsert)

        //act
        val actualItems = taggedItemDao.getAll()

        //assert
        assertThat(actualItems).isNotEmpty()
        assertThat(actualItems).hasSize(expectedItemsToInsert.size)
        assertThat(actualItems).isEqualTo(expectedItemsToInsert)
    }

    private object Fixtures {
        fun taggedItem(id: String) = TaggedItemEntity(
            id = id,
            title = "My item $id",
            description = "My desc $id",
            rating = 5,
            imagePath = "https://tag.com/$id.jpg",
            createdAt = 1648281996787
        )
    }
}