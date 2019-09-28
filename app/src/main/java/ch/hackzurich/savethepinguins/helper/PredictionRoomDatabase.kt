package ch.hackzurich.savethepinguins.helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ch.hackzurich.savethepinguins.dto.PredictionDao
import ch.hackzurich.savethepinguins.dto.PredictionSave

@Database(entities = arrayOf(PredictionSave::class), version = 1)
public abstract class PredictionRoomDatabase : RoomDatabase() {

    abstract fun predictionDao(): PredictionDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PredictionRoomDatabase? = null

        fun getDatabase(context: Context): PredictionRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PredictionRoomDatabase::class.java,
                    "room_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}