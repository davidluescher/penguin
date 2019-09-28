package ch.hackzurich.savethepinguins.dto

import com.google.gson.annotations.SerializedName

data class ImageServiceRequest(
    @SerializedName("file")
    val file: String
)