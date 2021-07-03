package me.taggerapp.android.signIn

object ModuleFactory {

    fun getSignUpController(): SignUpController {
        val validateUserPin = ValidateNewUserPin()
        return SignUpController(validateUserPin)
    }
}