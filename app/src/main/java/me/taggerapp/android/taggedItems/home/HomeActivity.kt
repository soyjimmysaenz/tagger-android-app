package me.taggerapp.android.taggedItems.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import me.taggerapp.android.databinding.ActivityHomeBinding
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
        val taggedItemModels = viewController.loadItems()
        taggedItemsAdapter.update(taggedItemModels)
        isLoading(false)
    }

    private fun isLoading(isLoading: Boolean) = with(binding) {
        progressItems.isVisible = isLoading
        val currentListViewCount = recyclerViewItems.adapter?.itemCount ?: 0
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
}