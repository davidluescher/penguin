package com.anychart.sample.charts

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore

import androidx.appcompat.app.AppCompatActivity
import ch.hackzurich.savethepinguins.R
import ch.hackzurich.savethepinguins.ui.HomeActivity
import ch.hackzurich.savethepinguins.ui.ImpactActivity

import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.SingleValueDataSet
import kotlinx.android.synthetic.main.activity_food_rating.*


class FoodRatingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_rating)

        val score = intent.getIntExtra(ImpactActivity.SCORE, 0)
        val name = intent.getStringExtra(ImpactActivity.NAME)
        lblTitle.text = name
        lblMotivation.text = getString(
            if (score > 7) {
                R.string.motivation_bad
            } else if (score < 5) {
                R.string.motivation_good
            } else {
                R.string.motivation_neutral
            }
        )

        any_chart_view.setProgressBar(progress_bar)

        val circularGauge = AnyChart.circular()
        circularGauge.fill("white")
            .stroke(null)
            .padding(0, 0, 0, 0)
            .margin(30, 30, 30, 30)
        circularGauge.startAngle(0)
            .sweepAngle(360)

        /* TODO: Set value of gauge needle */
        val currentValue = 13.8
        circularGauge.data(SingleValueDataSet(arrayOf(currentValue)))

        circularGauge.axis(0)
            .startAngle(-150)
            .radius(80)
            .sweepAngle(300)
            .width(3)
        /*.ticks("{ type: 'line', length: 4, position: 'outside' }")*/

        /*circularGauge.axis(0).labels().position("outside")*/

        /* TODO: Set min and max values for CO2/g" */
        circularGauge.axis(0).scale()
            .minimum(0)
            .maximum(100)

        circularGauge.needle(0)
            .stroke(null)
            .startRadius("6%")
            .endRadius("38%")
            .startWidth("2%")
            .endWidth(0)

        /* Set the cap for the needle */
        circularGauge.cap()
            .radius("4%")
            .enabled(true)
            .stroke(null)


        /* First section of the gauge chart in green */
        circularGauge.range(
            0,
            "{\n" +
                    "    from: 0,\n" +
                    "    to: 10,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#045D56',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Second section of the gauge chart */
        circularGauge.range(
            1,
            "{\n" +
                    "    from: 10,\n" +
                    "    to: 20,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#1EB980',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Third section of the gauge chart */
        circularGauge.range(
            2,
            "{\n" +
                    "    from: 20,\n" +
                    "    to: 30,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#AED581',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Fourth section of the gauge chart */
        circularGauge.range(
            3,
            "{\n" +
                    "    from: 30,\n" +
                    "    to: 40,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#C0CA33',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Fifth section of the gauge chart */
        circularGauge.range(
            4,
            "{\n" +
                    "    from: 40,\n" +
                    "    to: 50,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#FFEB3B',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Sixth section of the gauge chart */
        circularGauge.range(
            5,
            "{\n" +
                    "    from: 50,\n" +
                    "    to: 60,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#FFC107',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Seventh section of the gauge chart */
        circularGauge.range(
            6,
            "{\n" +
                    "    from: 60,\n" +
                    "    to: 70,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#FFA000',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Eighth section of the gauge chart */
        circularGauge.range(
            7,
            "{\n" +
                    "    from: 70,\n" +
                    "    to: 80,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#EF6C00',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Ninth section of the gauge chart */
        circularGauge.range(
            8,
            "{\n" +
                    "    from: 80,\n" +
                    "    to: 90,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#FF8A65',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }"
        )

        /* Tenth section of the gauge chart */
        circularGauge.range(
            9,
            ("{\n" +
                    "    from: 80,\n" +
                    "    to: 100,\n" +
                    "    position: 'inside',\n" +
                    "    fill: '#BF360C',\n" +
                    "    stroke: '1 #000',\n" +
                    "    startSize: 6,\n" +
                    "    endSize: 6,\n" +
                    "    radius: 80,\n" +
                    "    zIndex: 1\n" +
                    "  }")
        )

        any_chart_view.setChart(circularGauge)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val filepath: String? = intent.getStringExtra(HomeActivity.PHOTO_PATH)
        if (filepath != null) {
            setPic(filepath)
        } else {
            val fileUri: Uri = intent.getParcelableExtra(HomeActivity.PHOTO_URI) as Uri
            setPic(getPicturePath(fileUri))
        }
    }


    fun getPicturePath(selectedImage: Uri): String {
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
        val targetW: Int = imgMeal.width
        val targetH: Int = imgMeal.height

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
            imgMeal.setImageBitmap(bitmap)
        }
    }
}