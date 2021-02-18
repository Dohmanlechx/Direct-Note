package com.dohman.directnote

import android.content.Context

object Prefs {
    private const val PREFS_NAME = "DIRECT_NOTE_PREFS"
    private const val PRIVATE_MODE = 0

    private const val PREFS_PINCH_SCALE_FACTOR = "PREFS_PINCH_SCALE_FACTOR"

    private const val PREFS_DARK_MODE = "PREFS_DARK_MODE"
    private const val PREFS_DEFAULT_DARK_MODE = true

    fun getSeekbarProgress(ctx: Context): Float {
        val prefs = ctx.getSharedPreferences(PREFS_NAME, PRIVATE_MODE)
        return prefs.getFloat(PREFS_PINCH_SCALE_FACTOR, Constants.DEFAULT_FONT_SIZE)
    }

    fun saveSeekbarProgress(ctx: Context, factor: Float) {
        val prefs = ctx.getSharedPreferences(PREFS_NAME, PRIVATE_MODE)
        val editor = prefs.edit()
        editor.putFloat(PREFS_PINCH_SCALE_FACTOR, factor)
        editor.apply()
    }

    fun saveDarkModeValue(ctx: Context, isDarkMode: Boolean) {
        val prefs = ctx.getSharedPreferences(PREFS_NAME, PRIVATE_MODE)
        val editor = prefs.edit()
        editor.putBoolean(PREFS_DARK_MODE, isDarkMode)
        editor.apply()
    }

    fun isDarkModeChosen(ctx: Context): Boolean {
        val prefs = ctx.getSharedPreferences(PREFS_NAME, PRIVATE_MODE)
        return prefs.getBoolean(PREFS_DARK_MODE, PREFS_DEFAULT_DARK_MODE)
    }
}