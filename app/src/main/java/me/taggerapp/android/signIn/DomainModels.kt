package me.taggerapp.android.signIn

data class UserPinRequest(
    val pin: String?
) {
    companion object {
        const val PIN_LENGTH = 6
    }
}

