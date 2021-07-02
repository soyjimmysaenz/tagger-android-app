package me.taggerapp.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.taggerapp.android.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        setupViewBinding()
    }

    private fun setupViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}