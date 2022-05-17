package com.dohman.directnote

import android.content.Context

object Prefs {
    private const val PREFS_NAME = "DIRECT_NOTE_PREFS"

    private const val PREFS_PINCH_SCALE_FACTOR = "PREFS_PINCH_SCALE_FACTOR"

    private const val PREFS_DARK_MODE = "PREFS_DARK_MODE"
    private const val PREFS_DEFAULT_DARK_MODE = true

    fun getSeekbarProgress(ctx: Context): Float {
        val prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getFloat(PREFS_PINCH_SCALE_FACTOR, Constants.DEFAULT_FONT_SIZE)
    }

    fun saveSeekbarProgress(ctx: Context, factor: Float) {
        val prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putFloat(PREFS_PINCH_SCALE_FACTOR, factor)
        editor.apply()
    }

    fun saveDarkModeValue(ctx: Context, isDarkMode: Boolean) {
        val prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean(PREFS_DARK_MODE, isDarkMode)
        editor.apply()
    }

    fun isDarkModeChosen(ctx: Context): Boolean {
        val prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(PREFS_DARK_MODE, PREFS_DEFAULT_DARK_MODE)
    }
}