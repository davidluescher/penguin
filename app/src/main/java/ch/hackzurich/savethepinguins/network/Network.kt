package ch.hackzurich.savethepinguins.network

import ch.hackzurich.savethepinguins.dto.Prediction
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object Network {
    private val url: String = "https://my-json-server.typicode.com/omnimolecule/HackZurich19Json/"
    private val predictions: String = "predictions"

    fun getPrediction(id: Int, delegate: PredictionReceived) {
        val mURL = URL(url + predictions + "/" + id)

        with(mURL.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "GET"

            println("URL : $url")
            println("Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                val obj = Json.nonstrict.parse(Prediction.serializer(), response.toString())
                delegate.predictionReceived(obj)
            }
        }
    }

    interface PredictionReceived {
        fun predictionReceived(prediction: Prediction)

        fun errorOccured()
    }
}