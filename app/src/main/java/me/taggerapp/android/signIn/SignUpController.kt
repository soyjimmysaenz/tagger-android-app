package me.taggerapp.android.signIn

import java.lang.Exception

class SignUpController(
    private val userSessionRepository: UserSessionRepository
) {

    fun submit(pinText: String?): Boolean {
        val pinRequest = UserPinRequest(pinText)
        val isValidPin = validateNewPin(pinRequest)
        if (!isValidPin) return isValidPin

        return savePin(pinRequest)
    }

    private fun validateNewPin(userPinRequest: UserPinRequest): Boolean {
        return userPinRequest.pin?.length == UserPinRequest.PIN_LENGTH
    }

    private fun savePin(userPinRequest: UserPinRequest): Boolean {
        //TODO: implementar tests (instrumented y local)
        return try {
            userSessionRepository.savePin(userPinRequest)
            true
        } catch (error: Exception) {
            //TODO: esto deberia ser mapeado y propagado
            false
        }
    }
}