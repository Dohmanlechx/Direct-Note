package com.dohman.directnote

import android.content.Context

object Prefs {
    private const val PREFS_NAME = "DIRECT_NOTE_PREFS"
    private const val PRIVATE_MODE = 0

    private const val PREFS_TEXT_SIZE = "PREFS_TEXT_SIZE"
    private const val PREFS_DEFAULT_TEXT_SIZE = 12f

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
}