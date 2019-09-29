package ch.hackzurich.savethepinguins.helper

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesHelper {

    private fun getSharedPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences("Pingu", Context.MODE_PRIVATE)
    }

    fun writeScore(context: Context, score: Int) {
        val sharedPrefs = getSharedPrefs(context)
        val overallScore = sharedPrefs.getInt("overallScore", 0)
        val numScore = sharedPrefs.getInt("noOfScores", 0)
        getSharedPrefs(context).edit()
            .putInt("overallScore", overallScore + score)
            .putInt("noOfScores", numScore + 1)
            .apply()

    }

    fun getScore(context: Context): Int {
        val sharedPrefs = getSharedPrefs(context)
        val overallScore = sharedPrefs.getInt("overallScore", 0)
        val numScore = sharedPrefs.getInt("noOfScores", 0)
        if (numScore == 0) {
            writeScore(context, 5)
            return 5
        }
        return overallScore / numScore
    }
}