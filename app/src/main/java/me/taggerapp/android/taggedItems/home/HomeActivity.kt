package me.taggerapp.android.taggedItems.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import me.taggerapp.android.R
import me.taggerapp.android.databinding.ActivityHomeBinding
import me.taggerapp.android.taggedItems.ModuleFactory
import me.taggerapp.android.taggedItems.TaggedItem

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

    private fun setup() {
        setupViewBinding()
        setupViews()
        requestItems()
    }

    private fun setupViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupViews() {
        with (binding) {
            recyclerViewItems.adapter = taggedItemsAdapter
            recyclerViewItems.layoutManager = LinearLayoutManager(this@HomeActivity)
            recyclerViewItems.setHasFixedSize(true)

            buttonAddItem.setOnClickListener {
                onNewItemButtonSelected()
            }
        }
    }

    private fun requestItems() {
        val taggedItemModels = viewController.loadItems()
        taggedItemsAdapter.update(taggedItemModels)
    }

    private fun onTaggedItemSelected(taggedItem: TaggedItem) {
        Log.d(TAG, "Tagged item selected: $taggedItem")
        Toast.makeText(this, "Seleccionaste ${taggedItem.title}", Toast.LENGTH_SHORT).show()
        //TODO: presentar dialog con detalle de item
    }
    
    private fun onNewItemButtonSelected() {
        val (savedItem, currentItems) = viewController.addTaggedItem()
        val isSavedSuccessfully = savedItem != null

        if (isSavedSuccessfully) {
            taggedItemsAdapter.update(currentItems)
        }

        @StringRes val stringId = if (isSavedSuccessfully)
            R.string.item_saved else R.string.error_saving_item
        Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show()
    }
}