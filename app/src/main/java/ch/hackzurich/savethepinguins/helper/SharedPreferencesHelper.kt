package ch.hackzurich.savethepinguins.helper

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesHelper {

    private fun getSharedPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences("Pingu", Context.MODE_PRIVATE)
    }

    fun writeScore(context: Context, score: Int) {
        getSharedPrefs(context).edit().putInt("score", score).apply()
    }

    fun getScore(context: Context): Int {
        return getSharedPrefs(context).getInt("score", 5)
    }
}