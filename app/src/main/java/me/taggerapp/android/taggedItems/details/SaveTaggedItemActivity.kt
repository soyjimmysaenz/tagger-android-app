package me.taggerapp.android.taggedItems.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.taggerapp.android.R
import me.taggerapp.android.databinding.ActivitySaveTaggedItemBinding
import me.taggerapp.android.taggedItems.ModuleFactory
import me.taggerapp.android.taggedItems.TaggedItem

class SaveTaggedItemActivity : AppCompatActivity() {

    companion object {
        const val TAG = "SaveTaggedItemActivity"

        fun createIntent(context: Context, model: TaggedItem): Intent {
            val intent = Intent(context, SaveTaggedItemActivity::class.java)
            intent.putExtra(SaveTaggedItemController.ARG_MODEL, model)
            return intent
        }
    }

    private lateinit var binding: ActivitySaveTaggedItemBinding

    private val viewController: SaveTaggedItemController by lazy {
        ModuleFactory.getSaveController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        setupViewBinding()
        val couldSetupController = setupController()
        if (!couldSetupController) return
        setupToolbar()
    }

    private fun setupViewBinding() {
        binding = ActivitySaveTaggedItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupController(): Boolean {
        return try {
            viewController.setupWith(intent.extras)
            true
        } catch (error: Throwable) {
            Log.e(TAG, error.message.toString())
            Toast
                .makeText(this, getString(R.string.error_loading_item), Toast.LENGTH_SHORT)
                .show()
            finish()
            false
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.topBar)
        supportActionBar?.title = viewController.currentModel.title
    }
}