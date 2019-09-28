package ch.hackzurich.savethepinguins.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.hackzurich.savethepinguins.R
import ch.hackzurich.savethepinguins.dto.PredictionSave

class HistoryAdapter(val context: Context) : RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {
    private var dataSet: MutableList<PredictionSave> = mutableListOf()

    fun addData(predictions: List<PredictionSave>) {
        dataSet.addAll(predictions)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false) as RelativeLayout
        return MyViewHolder(item)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val prediction = dataSet[position]
        val dateString =
            android.text.format.DateFormat.format("dd. MMM yyyy - HH:mm", prediction.datetime)
                .toString()
        holder.lblDate.text = dateString
        holder.lblName.text = prediction.name
        holder.lblScore.text = prediction.score.toString()
        holder.imgMealy.setImageBitmap(getPic(prediction.filePath))
    }

    private fun getPic(currentPhotoPath: String): Bitmap? {

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
            return bitmap
        }
        return null
    }

    class MyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent) {
        var lblName: TextView
        var lblDate: TextView
        var lblScore: TextView
        var imgMealy: ImageView

        init {
            lblName = parent.findViewById(R.id.lblName)
            lblDate = parent.findViewById(R.id.lblDate)
            lblScore = parent.findViewById(R.id.lblScore)
            imgMealy = parent.findViewById(R.id.imgMealy)
        }
    }
}