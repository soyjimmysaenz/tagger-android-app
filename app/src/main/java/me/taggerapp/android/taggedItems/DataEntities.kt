package me.taggerapp.android.taggedItems

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tagged_items")
data class TaggedItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String? = null,
    @ColumnInfo(name = "rating")
    val rating: Int,
    @ColumnInfo(name = "image_path")
    val imagePath: String? = null,
    @ColumnInfo(name = "created_at")
    val createdAt: Long
)

data class TaggedItemDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("imagePath")
    val imagePath: String?,
    @SerializedName("createdAt")
    val createdAt: Long
)