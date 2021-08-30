package me.taggerapp.android.helpers

fun Float?.toIntOrNull(): Int? {
    if (this == null) return null
    return try {
        this.toInt()
    } catch (error: Throwable) {
        null
    }
}