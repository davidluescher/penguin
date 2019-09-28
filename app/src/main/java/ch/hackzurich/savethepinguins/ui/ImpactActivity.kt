package ch.hackzurich.savethepinguins.ui

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import ch.hackzurich.savethepinguins.R
import ch.hackzurich.savethepinguins.dto.Prediction
import ch.hackzurich.savethepinguins.helper.SharedPreferencesHelper
import ch.hackzurich.savethepinguins.network.Network
import com.anychart.sample.charts.FoodRatingActivity
import kotlinx.android.synthetic.main.activity_impact.*


class ImpactActivity : AppCompatActivity(), Network.PredictionReceived {
    var score: Int = 0

    companion object {
        val SCORE = "Score"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_impact)

        val webSettings = web_view.settings
        webSettings.javaScriptEnabled = true

        btnAction.setOnClickListener {
            val intent = Intent(this, FoodRatingActivity::class.java)
            intent.putExtra(SCORE, score)
            startActivity(intent)
            finish()
        }

        AsyncTask.execute {
            val filepath: String? = intent.getStringExtra(HomeActivity.PHOTO_PATH)
            var id = 0
            if (filepath != null) {
                //notYetSupported
            } else {
                val fileUri: Uri = intent.getParcelableExtra(HomeActivity.PHOTO_URI) as Uri
                val picturePath = getPicturePath(fileUri)
                if (picturePath.endsWith("pizza2.jpg")) {
                    id = 1
                } else if (picturePath.endsWith("lasagne.jpg")) {
                    id = 3
                } else if (picturePath.endsWith("hotdog.jpg")) {
                    id = 2
                } else if (picturePath.endsWith("plum.jpg")) {
                    id = 0
                }
            }
            Network.getPrediction(id, this)
        }
    }

    override fun predictionReceived(prediction: Prediction) {
        score = prediction.overallScore
        SharedPreferencesHelper.writeScore(this, score)
        val gifPenguin =
            if (prediction.overallScore > 7) {
                "file:android_asset/penguin_angry.gif"
            } else if (prediction.overallScore < 5) {
                "file:android_asset/penguin_dancing.gif"
            } else {
                "file:android_asset/penguin_business.gif"
            }

        runOnUiThread { web_view.loadUrl(gifPenguin) }

    }

    override fun errorOccured() {
        //Oops?
    }


/*
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val filepath: String? = intent.getStringExtra(HomeActivity.PHOTO_PATH)
        if (filepath != null) {
            setPic(filepath)
        } else {
            val fileUri: Uri = intent.getParcelableExtra(HomeActivity.PHOTO_URI) as Uri
            setPic(getPicture(fileUri))
        }
    }*/


    fun getPicturePath(selectedImage: Uri): String {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(selectedImage, filePathColumn, null, null, null)
        cursor.moveToFirst()
        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        val picturePath = cursor.getString(columnIndex)
        cursor.close()
        return picturePath
    }
/*
    private fun setPic(currentPhotoPath: String) {
        // Get the dimensions of the View
        val targetW: Int = imgTest.width
        val targetH: Int = imgTest.height

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true

            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = 1//Math.min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inPurgeable = true
        }
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap ->
            imgTest.setImageBitmap(bitmap)
        }
    }
 */
}
