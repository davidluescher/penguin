package ch.hackzurich.savethepinguins.network

import ch.hackzurich.savethepinguins.dto.FakeImageServiceRequest
import ch.hackzurich.savethepinguins.dto.ImageServiceRequest
import ch.hackzurich.savethepinguins.dto.Prediction
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface VisionService {

    @Headers("Authorization: Bearer:5ced68e643ecc02d1bc07b557c941be2aa90976b")
    @POST("/vision/")
    fun getPrediction(@Header("Authorization") authorization: String, @Body request: ImageServiceRequest): Call<Prediction>

    @Headers("Authorization: Bearer:5ced68e643ecc02d1bc07b557c941be2aa90976b")
    @Multipart
    @POST("/vision/")
    fun getPredictionMultipart(@Header("Authorization") authorization: String, @Part file: MultipartBody.Part): Call<Prediction>

    @Headers("Authorization: Bearer:5ced68e643ecc02d1bc07b557c941be2aa90976b")
    @POST("/vision")
    fun getPredictionFake(@Header("Authorization") authorization: String, @Body request: FakeImageServiceRequest): Call<Prediction>
}