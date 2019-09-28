package ch.hackzurich.savethepinguins.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class ScoredItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("details")
    val details: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("ingredients")
    val ingredients: List<Ingredient>
)