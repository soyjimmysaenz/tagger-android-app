package me.taggerapp.android.taggedItems.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import me.taggerapp.android.databinding.ActivityHomeBinding
import me.taggerapp.android.taggedItems.ModuleFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewController: HomeController by lazy {
        ModuleFactory.getHomeController()
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
        //TODO: configurar RecyclerView, Adapter, etc.
    }

    private fun requestItems() {
        val taggedItemModels = viewController.getTaggedItems()
        Log.d(TAG, "Tagged items: $taggedItemModels")
        //TODO: cargar data en adapter->recyclerview
    }
}