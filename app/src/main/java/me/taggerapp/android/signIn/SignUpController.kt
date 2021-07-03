package me.taggerapp.android.signIn

class SignUpController(
    private val validateNewPin: ValidateNewUserPin,
    private val savePin: SaveUserPin
) {

    fun submit(pinText: String?): Boolean {
        val pinRequest = UserPinRequest(pinText)
        val isValidPin = validateNewPin(pinRequest)
        if (!isValidPin) return isValidPin

        return savePin(pinRequest)
    }
}