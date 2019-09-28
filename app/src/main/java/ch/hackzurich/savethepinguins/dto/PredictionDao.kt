package ch.hackzurich.savethepinguins.dto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PredictionDao {

    @Query("SELECT * from prediction_table ORDER BY datetime DESC")
    fun getAllPredictions(): List<PredictionSave>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(predictionSave: PredictionSave)

}
