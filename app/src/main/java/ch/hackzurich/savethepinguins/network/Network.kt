package ch.hackzurich.savethepinguins.network

import android.util.Log
import ch.hackzurich.savethepinguins.dto.Prediction
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

object Network {
    private val url: String = "https://api-beta.bite.ai/"
    private val vision: String = "vision/"
    private val message: String = "{\n" +
            "\"source_url\":\"https://thumbs-prod.si-cdn.com/_oO5E4sOE9Ep-qk_kuJ945_-qo4=/800x600/filters:no_upscale()/https://public-media.si-cdn.com/filer/d5/24/d5243019-e0fc-4b3c-8cdb-48e22f38bff2/istock-183380744.jpg\"\n" +
            "}"

    private val response: String = ""

    fun getPrediction(): Prediction {

        val serverURL: String = url + vision
        val url = URL(serverURL)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.connectTimeout = 300000
        connection.connectTimeout = 300000
        connection.doOutput = true
        connection.doInput = true

        val postData: ByteArray = message.toByteArray(StandardCharsets.UTF_8)

        connection.setRequestProperty("charset", "utf-8")
        connection.setRequestProperty(
            "Authorization",
            "Bearer:5ced68e643ecc02d1bc07b557c941be2aa90976b"
        )
        connection.setRequestProperty("Content-lenght", postData.size.toString())
        connection.setRequestProperty("Content-Type", "application/json")

        try {
            val outputStream = DataOutputStream(connection.outputStream)
            outputStream.write(postData)
            outputStream.flush()
        } catch (exception: Exception) {
            Log.e("Blaaa", exception.localizedMessage)
        }

        if (connection.responseCode != HttpURLConnection.HTTP_OK && connection.responseCode != HttpURLConnection.HTTP_CREATED) {
            Log.e("Blaae", connection.responseCode.toString())
        } else {
            BufferedReader(InputStreamReader(connection.inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                val obj = Json.nonstrict.parse(Prediction.serializer(), response.toString())
                return obj
            }
        }
        return Prediction(0, "", "", ArrayList())
    }

}