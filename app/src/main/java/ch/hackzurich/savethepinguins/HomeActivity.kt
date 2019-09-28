package ch.hackzurich.savethepinguins

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import kotlinx.android.synthetic.main.activity_home.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {
    companion object {
        val PHOTO_PATH: String = "PhotoPath"
        val PHOTO_URI: String = "PhotoUri"
    }

    private var currentPhotoPath: String? = null
    private val REQUEST_TAKE_PHOTO: Int = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btnAction.setOnClickListener {
            runWithPermissions(Manifest.permission.READ_EXTERNAL_STORAGE) {
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                    // Ensure that there's a camera activity to handle the intent
                    if (takePictureIntent.resolveActivity(packageManager) != null) {
                        // Create the File where the photo should go
                        val photoFile: File? = try {
                            createImageFile()
                        } catch (ex: IOException) {
                            // Error occurred while creating the File
                            Toast.makeText(this, "Error while creating the file", Toast.LENGTH_LONG)
                                .show()
                            null
                        }
                        // Continue only if the File was successfully created
                        photoFile?.also {
                            val photoURI: Uri = FileProvider.getUriForFile(
                                this,
                                "ch.hackzurich.savethepinguins",
                                it
                            )
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                            val requestFileIntent = Intent(Intent.ACTION_PICK).apply {
                                type = "image/jpg"
                            }

                            val chooser = Intent.createChooser(requestFileIntent, "Some text here")
                            chooser.putExtra(
                                Intent.EXTRA_INITIAL_INTENTS,
                                arrayOf(takePictureIntent)
                            )
                            startActivityForResult(chooser, REQUEST_TAKE_PHOTO)
                        }
                    } else {
                        val requestFileIntent = Intent(Intent.ACTION_PICK).apply {
                            type = "image/jpg"
                        }
                        startActivityForResult(requestFileIntent, REQUEST_TAKE_PHOTO)
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            val intent = Intent(this, ImpactActivity::class.java)
            if (data?.data == null) {
                intent.putExtra(PHOTO_PATH, currentPhotoPath)
            } else {
                data.data?.also { returnUri ->
                    intent.putExtra(PHOTO_URI, returnUri)
                }
            }
            startActivity(intent)

        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }
}
