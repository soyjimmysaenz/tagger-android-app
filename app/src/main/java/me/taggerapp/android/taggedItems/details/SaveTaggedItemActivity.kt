package me.taggerapp.android.taggedItems.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.taggerapp.android.databinding.ActivitySaveTaggedItemBinding
import me.taggerapp.android.taggedItems.TaggedItem

class SaveTaggedItemActivity : AppCompatActivity() {

    companion object {
        const val TAG = "SaveTaggedItemActivity"

        fun createIntent(context: Context, model: TaggedItem): Intent {
            //TODO: agregar objeto a intent
            return Intent(context, SaveTaggedItemActivity::class.java)
        }
    }

    private lateinit var binding: ActivitySaveTaggedItemBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        setupViewBinding()
        setupToolbar()
    }

    private fun setupViewBinding() {
        binding = ActivitySaveTaggedItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupToolbar(title: String? = null) {
        setSupportActionBar(binding.topBar)
        title?.let { titleValue ->
            binding.topBar.title = titleValue
        }
    }
}