package ch.hackzurich.savethepinguins.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import ch.hackzurich.savethepinguins.R
import ch.hackzurich.savethepinguins.network.Network
import ch.hackzurich.savethepinguins.network.RetrofitClientInstance
import ch.hackzurich.savethepinguins.network.VisionService
import kotlinx.android.synthetic.main.activity_impact.*
import java.io.ByteArrayOutputStream


class ImpactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_impact)
        btnAction.setOnClickListener {
            startActivity(Intent(this, DetailRatingActivity::class.java))
            finish()
        }
    }

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

            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val imageBytes = baos.toByteArray()
            val imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT)

            val service =
                RetrofitClientInstance.retrofit.create(VisionService::class.java)
            /*val request = ImageServiceRequest(imageString)
            val call = service.getPrediction("Bearer:5ced68e643ecc02d1bc07b557c941be2aa90976b", request)
            call.enqueue(object : Callback<Prediction> {
                override fun onResponse(call: Call<Prediction>, response: Response<Prediction>) {
                    Toast.makeText(this@ImpactActivity, "OK", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Prediction>, t: Throwable) {
                    Toast.makeText(this@ImpactActivity, "NOK", Toast.LENGTH_LONG).show()
                }
            })*/
            /*val filePart = MultipartBody.Part.createFormData(
                "file",
                File(currentPhotoPath).getName(),
                RequestBody.create(MediaType.parse("image/jpg"), File(currentPhotoPath))
            )
            val call2 =
                service.getPredictionMultipart("Bearer:5ced68e643ecc02d1bc07b557c941be2aa90976b", filePart)
            call2.enqueue(object : Callback<Prediction> {
                override fun onResponse(call: Call<Prediction>, response: Response<Prediction>) {
                    Toast.makeText(this@ImpactActivity, "OK", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Prediction>, t: Throwable) {
                    Toast.makeText(this@ImpactActivity, "NOK", Toast.LENGTH_LONG).show()
                }
            })*/
            /*val request = FakeImageServiceRequest()
            val call = service.getPredictionFake("Bearer:5ced68e643ecc02d1bc07b557c941be2aa90976b", request)
            call.enqueue(object : Callback<Prediction> {
                override fun onResponse(call: Call<Prediction>, response: Response<Prediction>) {
                    Toast.makeText(this@ImpactActivity, "OK", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Prediction>, t: Throwable) {
                    Toast.makeText(this@ImpactActivity, "NOK", Toast.LENGTH_LONG).show()
                }
            })*/
            AsyncTask.execute {
                Network.getPrediction()
            }
        }
    }
}

