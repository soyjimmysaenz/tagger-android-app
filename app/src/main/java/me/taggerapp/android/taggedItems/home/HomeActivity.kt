package me.taggerapp.android.taggedItems.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import me.taggerapp.android.R
import me.taggerapp.android.databinding.ActivityHomeBinding
import me.taggerapp.android.helpers.LoadingDataException
import me.taggerapp.android.taggedItems.ModuleFactory
import me.taggerapp.android.taggedItems.TaggedItem
import me.taggerapp.android.taggedItems.details.SaveTaggedItemActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val taggedItemsAdapter: TaggedItemsListAdapter by lazy {
        TaggedItemsListAdapter(::onTaggedItemSelected)
    }
    private val viewController: HomeController by lazy {
        ModuleFactory.getHomeController(this)
    }
    private val currentListViewCount: Int
        get() = binding.recyclerViewItems.adapter?.itemCount ?: 0

    companion object {
        const val TAG = "HomeActivity"

        fun createIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    override fun onResume() {
        super.onResume()
        requestItems()
    }

    private fun setup() {
        setupViewBinding()
        setupViews()
    }

    private fun setupViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupViews() {
        with(binding) {
            recyclerViewItems.apply {
                val context = this@HomeActivity
                adapter = taggedItemsAdapter
                val manager = LinearLayoutManager(context)
                layoutManager = manager
                setHasFixedSize(true)
                val divider = DividerItemDecoration(context, manager.orientation)
                addItemDecoration(divider)
            }

            buttonAddItem.setOnClickListener {
                onNewItemButtonSelected()
            }
        }
    }

    private fun requestItems() = lifecycleScope.launch {
        isLoading(true)
        try {
            val taggedItemModels = viewController.loadItems()
            taggedItemsAdapter.update(taggedItemModels)
            isLoading(false)
        } catch (error: LoadingDataException) {
            isLoading(false)
            showError(error, ::tryReloadItems)
        }
    }

    private fun tryReloadItems() {
        requestItems()
    }

    private fun isLoading(isLoading: Boolean) = with(binding) {
        errorStateView.isVisible = false
        progressItems.isVisible = isLoading

        if (currentListViewCount > 0) {
            recyclerViewItems.isVisible = true
            listViewItemsOverlay.isVisible = isLoading
        } else {
            recyclerViewItems.isVisible = !isLoading
        }
        buttonAddItem.isEnabled = !isLoading
    }

    private fun onTaggedItemSelected(taggedItem: TaggedItem) {
        Log.d(TAG, "Tagged item selected: $taggedItem")
        //TODO: presentar dialog con detalle de item
        navigateToDetails(taggedItem)
    }

    private fun onNewItemButtonSelected() = lifecycleScope.launch {
        navigateToDetails(null)
    }

    private fun navigateToDetails(selectedItem: TaggedItem?) {
        val intent = SaveTaggedItemActivity.createIntent(this, selectedItem)
        startActivity(intent)
    }

    private fun showError(error: LoadingDataException, onTap: (() -> Unit)? = null) {
        if (currentListViewCount > 0) {
            val errorMessage = error.shortMessage ?: error.technicalMessage
            Snackbar
                .make(binding.root, errorMessage, Snackbar.LENGTH_LONG)
                .setBackgroundTint(ContextCompat.getColor(this, R.color.background_error))
                .show()
        } else {
            binding.recyclerViewItems.isVisible = false
            val errorMessage = error.longMessage ?: error.technicalMessage
            with(binding.errorStateView) {
                isVisible = true
                text = errorMessage

                onTap?.let { tapEvent ->
                    setOnClickListener {
                        tapEvent()
                    }
                }
            }
        }
    }
}