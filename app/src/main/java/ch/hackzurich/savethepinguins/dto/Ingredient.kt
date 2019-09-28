package ch.hackzurich.savethepinguins.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    @SerializedName("id")
    val id: Int,
    @SerializedName("amound")
    val amound: Int,
    @SerializedName("grams")
    val grams: Int,
    @SerializedName("unit")
    val unit: String,
    @SerializedName("unit_details")
    val unit_details: String,
    @SerializedName("item")
    val item: ItemSummary
)