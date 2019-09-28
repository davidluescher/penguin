package ch.hackzurich.savethepinguins.dto

import kotlinx.serialization.Serializable

@Serializable
data class Predictions(val predictions: List<Prediction>)