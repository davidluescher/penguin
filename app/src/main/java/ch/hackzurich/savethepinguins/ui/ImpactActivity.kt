package ch.hackzurich.savethepinguins.ui

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.WebSettings
import androidx.appcompat.app.AppCompatActivity
import ch.hackzurich.savethepinguins.R
import com.anychart.sample.charts.FoodRatingActivity
import kotlinx.android.synthetic.main.activity_impact.*

class ImpactActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_impact)

        val webSettings = web_view.settings
        webSettings.javaScriptEnabled = true

        val gif_penguin = "file:android_asset/penguin_business.gif"
        web_view.loadUrl(gif_penguin)

        btnAction.setOnClickListener {
            startActivity(Intent(this, FoodRatingActivity::class.java))
            finish()
        }

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
    }


    fun getPicture(selectedImage: Uri): String {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(selectedImage, filePathColumn, null, null, null)
        cursor.moveToFirst()
        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        val picturePath = cursor.getString(columnIndex)
        cursor.close()
        return picturePath
    }

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
