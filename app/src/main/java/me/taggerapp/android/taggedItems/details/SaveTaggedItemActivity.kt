package me.taggerapp.android.taggedItems.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import me.taggerapp.android.R
import me.taggerapp.android.databinding.ActivitySaveTaggedItemBinding
import me.taggerapp.android.helpers.FieldValidationException
import me.taggerapp.android.taggedItems.ModuleFactory
import me.taggerapp.android.taggedItems.TaggedItem

class SaveTaggedItemActivity : AppCompatActivity() {

    companion object {
        const val TAG = "SaveTaggedItemActivity"

        fun createIntent(context: Context, model: TaggedItem?): Intent {
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

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun setup() {
        setupViewBinding()
        val couldSetupController = setupController()
        if (!couldSetupController) return
        setupViews()
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
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            viewController.currentModel?.title?.let { itemTitle ->
                title = itemTitle
            }
        }
    }

    private fun setupViews() {
        setupToolbar()
        with(binding) {
            buttonSaveItem.setOnClickListener {
                saveItem()
            }
            buttonSaveItem.isVisible = viewController.canEdit()
        }

        viewController.currentParamsModel?.let { paramsModel ->
            with(binding) {
                editTextTitle.setText(paramsModel.title)
                editTextDescription.setText(paramsModel.description)
                paramsModel.rating?.let { rating ->
                    ratingBar.rating = rating
                }
            }
        }
    }

    private fun saveItem() = lifecycleScope.launch {
        isSaving(true)
        val params = buildParamsToSave()

        try {
            viewController.trySaveItem(params)
            onSavedItemSuccessfully()
        } catch (error: Throwable) {
            isSaving(false)
            when (error) {
                is FieldValidationException -> showErrorBy(error)
                else -> showSavingError(error.message)
            }
        }
    }

    private fun buildParamsToSave() = with(binding) {
        SaveTaggedItemParams(
            title = editTextTitle.text.toString(),
            description = editTextDescription.text.toString(),
            rating = ratingBar.rating
        )
    }

    private fun isSaving(isSaving: Boolean) = with(binding) {
        buttonSaveItem.isEnabled = !isSaving
        inputLayoutTitle.isEnabled = !isSaving
        inputLayoutDescription.isEnabled = !isSaving
        ratingBar.isEnabled = !isSaving
    }

    private fun onSavedItemSuccessfully() {
        isSaving(false)
        Toast.makeText(
            this@SaveTaggedItemActivity, R.string.item_saved, Toast.LENGTH_SHORT
        ).show()
        finish()
    }

    private fun showErrorBy(error: FieldValidationException) {
        resetFieldsErrorState()
        with(binding) {
            when (error.tag) {
                SaveTaggedItemController.FIELD_TITLE -> inputLayoutTitle.error = error.message
                SaveTaggedItemController.FIELD_RATING -> showSavingError(error.message)
                else -> showSavingError()
            }
        }
    }

    private fun resetFieldsErrorState() = with(binding) {
        inputLayoutTitle.error = null
        inputLayoutDescription.error = null
    }

    private fun showSavingError(errorMessage: String? = null) {
        val message = errorMessage ?: getString(R.string.error_saving_item)
        Snackbar
            .make(binding.root, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(this, R.color.background_error))
            .show()
    }
}