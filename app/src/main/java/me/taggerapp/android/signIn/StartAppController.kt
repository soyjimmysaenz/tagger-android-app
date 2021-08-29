package me.taggerapp.android.signIn

class StartAppController(
    private val userSessionRepository: UserSessionRepository
) {
    enum class NavigateTo {
        //TODO: implementar SIGN_IN
        SIGN_UP, HOME
    }

    fun getNavigationTarget(): NavigateTo {
        val userPin = getUserPin()
        return if (userPin.isNullOrBlank())
            NavigateTo.SIGN_UP
        else
            NavigateTo.HOME
    }

    private fun getUserPin(): String? {
        return try {
            userSessionRepository.getUserPin()
        } catch (error: Exception) {
            //TODO: esto deberia ser mapeado y propagado
            null
        }
    }
}