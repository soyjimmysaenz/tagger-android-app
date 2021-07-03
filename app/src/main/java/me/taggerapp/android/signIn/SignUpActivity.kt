package me.taggerapp.android.signIn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import me.taggerapp.android.R
import me.taggerapp.android.databinding.ActivitySignUpBinding
import me.taggerapp.android.helpers.hideKeyboard
import me.taggerapp.android.home.HomeActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewController: SignUpController by lazy {
        ModuleFactory.getSignUpController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        setupViewBinding()
        setupViews()
    }

    private fun setupViewBinding() {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupViews() {
        with(binding) {
            buttonSubmit.setOnClickListener {
                onSubmitButtonClick()
            }
        }
    }

    private fun onSubmitButtonClick() {
        val inputPin = binding.inputLayoutPin
        val pinText = inputPin.editText?.text?.toString()
        val isValidPin = viewController.submit(pinText)

        if (isValidPin) {
            inputPin.error = null
            hideKeyboard(inputPin)
            navigateToHome()
        } else {
            inputPin.error = getString(R.string.error_validating_pin)
        }
    }

    private fun navigateToHome() {
        Toast.makeText(this, getString(R.string.registered), Toast.LENGTH_SHORT).show()
        val homeIntent = HomeActivity.createIntent(this)
        startActivity(homeIntent)
        finish()
    }
}