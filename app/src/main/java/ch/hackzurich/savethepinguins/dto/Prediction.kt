package ch.hackzurich.savethepinguins.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Prediction(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("items")
    val items: List<ScoredItem>
)