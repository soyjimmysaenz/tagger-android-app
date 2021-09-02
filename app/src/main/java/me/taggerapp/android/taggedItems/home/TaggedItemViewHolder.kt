package me.taggerapp.android.taggedItems.home

import androidx.recyclerview.widget.RecyclerView
import me.taggerapp.android.databinding.ItemTaggedItemBinding
import me.taggerapp.android.providers.MediaProvider
import me.taggerapp.android.taggedItems.TaggedItem

class TaggedItemViewHolder(
    private val binding: ItemTaggedItemBinding,
    private val mediaProvider: MediaProvider = MediaProvider
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(taggedItem: TaggedItem, itemSelectedListener: (TaggedItem) -> Unit) {
        with(binding) {
            textViewTitle.text = taggedItem.title
            val description = "${taggedItem.sourceText} ${taggedItem.description ?: "..."}"
            textViewDescription.text = description
            textViewRating.text = taggedItem.ratingText

            val (imagePath, placeholderId) = taggedItem.imageData
            mediaProvider.loadInto(viewImage, imagePath, placeholderId)

            root.setOnClickListener {
                itemSelectedListener(taggedItem)
            }
        }
    }
}