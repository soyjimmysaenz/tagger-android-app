package me.taggerapp.android.taggedItems.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.taggerapp.android.databinding.ItemTaggedItemBinding
import me.taggerapp.android.taggedItems.TaggedItem

class TaggedItemsListAdapter(
    private val itemSelectedListener: (TaggedItem) -> Unit
) : RecyclerView.Adapter<TaggedItemViewHolder>() {

    private val modelList = mutableListOf<TaggedItem>()

    fun update(newList: List<TaggedItem>) {
        modelList.clear()
        modelList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaggedItemViewHolder {
        return TaggedItemViewHolder(
            ItemTaggedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaggedItemViewHolder, position: Int) {
        val itemModel = modelList[position]
        holder.bind(itemModel, itemSelectedListener)
    }

    override fun getItemCount() = modelList.size
}