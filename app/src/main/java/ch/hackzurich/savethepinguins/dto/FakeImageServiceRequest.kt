package ch.hackzurich.savethepinguins.dto

import com.google.gson.annotations.SerializedName

class FakeImageServiceRequest() {
    @SerializedName("source_url")
    var source_url: String =
        "https://thumbs-prod.si-cdn.com/_oO5E4sOE9Ep-qk_kuJ945_-qo4=/800x600/filters:no_upscale()/https://public-media.si-cdn.com/filer/d5/24/d5243019-e0fc-4b3c-8cdb-48e22f38bff2/istock-183380744.jpg"

    init {
        /*if (imagePath.endsWith("istock-183380744.jpg")){
            source_url = "https://thumbs-prod.si-cdn.com/_oO5E4sOE9Ep-qk_kuJ945_-qo4=/800x600/filters:no_upscale()/https://public-media.si-cdn.com/filer/d5/24/d5243019-e0fc-4b3c-8cdb-48e22f38bff2/istock-183380744.jpg"
        } else if (imagePath.equals("")){

        }*/
    }

}


