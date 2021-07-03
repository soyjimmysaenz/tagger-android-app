package me.taggerapp.android.signIn

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import me.taggerapp.android.taggedItems.home.HomeActivity

class StartActivity : AppCompatActivity() {

    private val viewController: StartAppController by lazy {
        ModuleFactory.getStartAppController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        when(viewController.getNavigationTarget()) {
            StartAppController.NavigateTo.SIGN_UP -> navigateToSignUp()
            StartAppController.NavigateTo.HOME -> navigateToHome()
        }
    }

    private fun navigateToSignUp() {
        val intent = SignUpActivity.createIntent(this)
        startActivity(intent)
        finish()
    }

    private fun navigateToHome() {
        val intent = HomeActivity.createIntent(this)
        startActivity(intent)
        finish()
    }
}