package me.taggerapp.android.helpers

data class LoadingDataException(
    val technicalMessage: String,
    val shortMessage: String? = null,
    val longMessage: String? = null,
    override val cause: Throwable? = null
) : Exception(technicalMessage, cause)