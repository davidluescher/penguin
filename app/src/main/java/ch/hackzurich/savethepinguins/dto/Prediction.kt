package ch.hackzurich.savethepinguins.dto

import kotlinx.serialization.Serializable

@Serializable
data class Prediction(
    val id: Int,
    val name: String,
    val ingredients: List<Ingredient>,
    val overallScore: Int
)