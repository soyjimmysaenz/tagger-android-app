package me.taggerapp.android.helpers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

@Suppress("MemberVisibilityCanBePrivate")
open class ScopedViewController(
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
): CoroutineScope {

    protected lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + mainDispatcher

    fun onCreate() {
        job = Job()
    }

    fun onDestroy() {
        job.cancel()
    }
}