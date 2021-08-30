package me.taggerapp.android.taggedItems.details

import android.os.Bundle
import me.taggerapp.android.taggedItems.TaggedItem
import me.taggerapp.android.taggedItems.TaggedItemsRepository

class SaveTaggedItemController(
    private val taggedItemsRepository: TaggedItemsRepository
) {

    companion object {
        const val ARG_MODEL = "args.model"
    }

    internal var currentModel: TaggedItem? = null
        private set

    fun setupWith(bundle: Bundle?) {
        if (bundle == null) throw IllegalStateException("Datos inválidos")
        if (!bundle.containsKey(ARG_MODEL)) throw IllegalStateException("Modelo inválido")
        currentModel = bundle.getParcelable(ARG_MODEL)
    }
}