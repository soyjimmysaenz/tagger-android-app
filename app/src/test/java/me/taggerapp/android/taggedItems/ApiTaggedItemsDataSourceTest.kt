package me.taggerapp.android.taggedItems

import kotlinx.coroutines.runBlocking
import me.taggerapp.android.enqueueResponse
import me.taggerapp.android.providers.networking.RetrofitProviderFactory
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ApiTaggedItemsDataSourceTest {

    private lateinit var mockServer: MockWebServer
    private lateinit var itemsService: TaggedItemsService

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()

        val mockServerBaseUri = mockServer.url("/").toString()
        itemsService = RetrofitProviderFactory.createService(
            TaggedItemsService::class.java, mockServerBaseUri
        )
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun `given getAll() when request is valid then return a success response with items`() = runBlocking {
        mockServer.enqueueResponse("responses/tagged_items_default_200.json", 200)
        val dataSource = ApiTaggedItemsDataSource(itemsService)
        val retrievedItems = dataSource.getAll()

        assertNotNull(retrievedItems)
        assertTrue(retrievedItems.any())
        assertEquals(4, retrievedItems.size)
    }

    @Test
    fun `given getAll() when request is valid then return a success empty response`() = runBlocking {
        mockServer.enqueueResponse("responses/tagged_items_empty_200.json", 200)
        val dataSource = ApiTaggedItemsDataSource(itemsService)
        val retrievedItems = dataSource.getAll()

        assertNotNull(retrievedItems)
        assertTrue(retrievedItems.isEmpty())
    }

    @Test(expected = retrofit2.HttpException::class)
    fun `given getAll() when request is invalid then return a bad request response`() = runBlocking {
        mockServer.enqueueResponse("responses/tagged_items_default_200.json", 400)
        val dataSource = ApiTaggedItemsDataSource(itemsService)
        dataSource.getAll()
        Unit
    }
}