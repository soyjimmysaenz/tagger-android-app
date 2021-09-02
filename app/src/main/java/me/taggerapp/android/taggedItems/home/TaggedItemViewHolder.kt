package me.taggerapp.android.taggedItems.home

import androidx.recyclerview.widget.RecyclerView
import me.taggerapp.android.databinding.ItemTaggedItemBinding
import me.taggerapp.android.taggedItems.TaggedItem

class TaggedItemViewHolder(
    private val binding: ItemTaggedItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(taggedItem: TaggedItem, itemSelectedListener: (TaggedItem) -> Unit) {
        with(binding) {
            textViewTitle.text = taggedItem.title
            val description = "${taggedItem.sourceText} ${taggedItem.description ?: "..."}"
            textViewDescription.text = description
            textViewRating.text = taggedItem.ratingText
            //TODO: implementar carga de imagen

            root.setOnClickListener {
                itemSelectedListener(taggedItem)
            }
        }
    }
}