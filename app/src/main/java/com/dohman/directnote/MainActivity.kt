package com.dohman.directnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt_main.post {
            setupEditTextListener()
            edt_main.requestFocus()
        }
    }

    private fun setupEditTextListener() = edt_main.setOnFocusChangeListener { _, hasFocus ->
        if (hasFocus) {
            this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        }
    }
}
