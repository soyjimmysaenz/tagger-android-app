package me.taggerapp.android.signIn

class ValidateNewUserPin {

    companion object {
        private const val PIN_LENGTH = 6
    }

    operator fun invoke(userPinRequest: UserPinRequest): Boolean {
        return userPinRequest.pin?.length == PIN_LENGTH
    }
}