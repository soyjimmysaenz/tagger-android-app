package me.taggerapp.android.signIn

class SignUpController(
    private val validateNewPin: ValidateNewUserPin
) {

    fun submit(pinText: String?): Boolean {
        val isValidPin = validateNewPin(UserPinRequest(pinText))
        return isValidPin
        //TODO: guardar pin en almacenamiento local (shared prefs)
    }
}