package com.dohman.directnote

import android.content.Context

object Prefs {
    private const val PREFS_NAME = "DIRECT_NOTE_PREFS"
    private const val PRIVATE_MODE = 0

    private const val PREFS_TEXT_SIZE = "PREFS_TEXT_SIZE"
    private const val PREFS_DEFAULT_TEXT_SIZE = 12f

    private const val PREFS_DARK_MODE = "PREFS_DARK_MODE"
    private const val PREFS_DEFAULT_DARK_MODE = true

    fun getTextSize(ctx: Context): Float {
        val prefs = ctx.getSharedPreferences(PREFS_NAME, PRIVATE_MODE)
        return prefs.getFloat(PREFS_TEXT_SIZE, PREFS_DEFAULT_TEXT_SIZE)
    }

    fun saveTextSize(ctx: Context, textSize: Float) {
        val prefs = ctx.getSharedPreferences(PREFS_NAME, PRIVATE_MODE)
        val editor = prefs.edit()
        editor.putFloat(PREFS_TEXT_SIZE, textSize)
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