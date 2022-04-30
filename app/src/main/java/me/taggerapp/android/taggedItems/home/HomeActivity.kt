package me.taggerapp.android.taggedItems.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import me.taggerapp.android.databinding.ActivityHomeBinding
import me.taggerapp.android.taggedItems.ModuleLocator
import me.taggerapp.android.taggedItems.domain.TaggedItem

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val taggedItemsAdapter: TaggedItemsListAdapter by lazy {
        TaggedItemsListAdapter(::onTaggedItemSelected)
    }
    private val viewController: HomeController by lazy {
        ModuleLocator.getHomeController(this)
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

            buttonAddItem.setOnClickListener { onButtonAddItemClicked() }
        }
    }

    private fun onButtonAddItemClicked() {
        lifecycleScope.launch {
            viewController.addAnItem()
        }
    }

    private fun requestItems() {
        lifecycleScope.launchWhenCreated {
            viewController.loadItems().collect { taggedItems ->
                taggedItemsAdapter.update(taggedItems)
            }
        }
    }

    private fun onTaggedItemSelected(taggedItem: TaggedItem) {
        Log.d(TAG, "Tagged item selected: $taggedItem")
        Toast.makeText(this, "Seleccionaste ${taggedItem.title}", Toast.LENGTH_SHORT).show()
        //TODO: presentar dialog con detalle de item
    }
}