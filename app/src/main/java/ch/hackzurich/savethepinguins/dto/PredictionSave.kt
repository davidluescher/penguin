package ch.hackzurich.savethepinguins.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prediction_table")
data class PredictionSave(
    @PrimaryKey val datetime: Long,
    val name: String,
    val score: Int,
    val filePath: String,
    val isPhoto: Boolean
) {
}