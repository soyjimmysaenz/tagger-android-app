package me.taggerapp.android.signIn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.taggerapp.android.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        setupViewBinding()
    }

    private fun setupViewBinding() {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}