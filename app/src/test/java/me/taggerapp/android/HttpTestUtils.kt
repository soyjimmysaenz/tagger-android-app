package me.taggerapp.android

import me.taggerapp.android.FileHelper.getFileContentFromResources
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

internal fun MockWebServer.enqueueResponse(filePath: String, code: Int) {
    val fileContent = getFileContentFromResources(filePath)
        ?: throw IllegalArgumentException("filePath")
    enqueue(
        MockResponse()
            .setResponseCode(code)
            .setBody(fileContent)
    )
}

private object FileHelper {

    fun getFileContentFromResources(filePath: String): String? {
        val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
        val source = inputStream?.let { inputStream.source().buffer() }
        return source?.readString(StandardCharsets.UTF_8)
    }
}