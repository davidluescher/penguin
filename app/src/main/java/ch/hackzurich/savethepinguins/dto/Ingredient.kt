package ch.hackzurich.savethepinguins.dto

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val id: Int,
    val name: String,
    val score: Int
)