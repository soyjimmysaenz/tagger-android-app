package me.taggerapp.android.taggedItems.details

import android.content.Context
import android.os.Bundle
import me.taggerapp.android.R
import me.taggerapp.android.helpers.FieldValidationException
import me.taggerapp.android.helpers.ValidationResult
import me.taggerapp.android.helpers.toIntOrNull
import me.taggerapp.android.taggedItems.ItemSource
import me.taggerapp.android.taggedItems.TaggedItem
import me.taggerapp.android.taggedItems.TaggedItemsRepository

class SaveTaggedItemController(
    private val taggedItemsRepository: TaggedItemsRepository,
    private val context: Context
) {

    companion object {
        const val ARG_MODEL = "args.model"
        const val FIELD_TITLE = "fields.title"
        const val FIELD_RATING = "fields.rating"
    }

    internal var currentModel: TaggedItem? = null
        private set

    internal val currentParamsModel: SaveTaggedItemParams?
        get() = currentModel?.toSaveParams()

    fun setupWith(bundle: Bundle?) {
        if (bundle == null) throw IllegalStateException("Datos inválidos")
        if (!bundle.containsKey(ARG_MODEL)) throw IllegalStateException("Modelo inválido")
        currentModel = bundle.getParcelable(ARG_MODEL)
    }

    suspend fun trySaveItem(params: SaveTaggedItemParams) {
        val validationResult = validateParamsToSave(params)
        if (validationResult is ValidationResult.Error) {
            throw FieldValidationException(validationResult)
        }

        //si currentModel tiene valor (actualizar item), copiar el elemento con los nuevos valores
        //si es null (nuevo item), crear un nuevo modelo para insertar
        val modelToSave = currentModel?.copyWith(params) ?: params.toModel()

        val isSaved = taggedItemsRepository.save(modelToSave)
        if (!isSaved) {
            throw IllegalStateException(context.getString(R.string.error_saving_item))
        }
    }

    private fun validateParamsToSave(params: SaveTaggedItemParams): ValidationResult = with(params) {
        fun error(stringId: Int, tag: String) = ValidationResult.Error(
            context.getString(stringId), tag
        )

        if (title.isNullOrEmpty()) {
            return error(R.string.error_invalid_title, FIELD_TITLE)
        }
        if (rating == null || rating <= 0f || rating.toIntOrNull() == null) {
            return error(R.string.error_invalid_rating, FIELD_RATING)
        }

        return ValidationResult.Success
    }

    private fun TaggedItem.copyWith(params: SaveTaggedItemParams): TaggedItem {
        val newItem = params.toModel()
        return copy(
            title = newItem.title,
            description = newItem.description,
            rating = newItem.rating,
            createdAt = newItem.createdAt
        )
    }

    private fun SaveTaggedItemParams.toModel(): TaggedItem = TaggedItem(
        id = taggedItemsRepository.generateId(),
        title = requireNotNull(title),
        description = description,
        rating = requireNotNull(rating).toInt(),
        imagePath = null,
        createdAt = taggedItemsRepository.generateNowMillis(),
        source = ItemSource.MEMORY
    )

    private fun TaggedItem.toSaveParams(): SaveTaggedItemParams = SaveTaggedItemParams(
        title = title,
        description = description,
        rating = rating.toFloat()
    )
}

data class SaveTaggedItemParams(val title: String?, val description: String?, val rating: Float?)