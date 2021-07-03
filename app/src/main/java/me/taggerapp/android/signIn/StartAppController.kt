package me.taggerapp.android.signIn

class StartAppController(
    private val getUserPin: GetUserPin
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
}