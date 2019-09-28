package ch.hackzurich.savethepinguins.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    var retrofit: Retrofit
    private val BASE_URL = "https://api-beta.bite.ai"

    init {
        retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}