package com.dohman.directnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt_main.post { setupEditText() }
        seekbar.post { setupSlider() }
        setupOnClickListeners()
    }

    override fun onResume() {
        super.onResume()
        edt_main.requestFocus()
    }

    override fun onPause() {
        super.onPause()
        edt_main.clearFocus()
    }

    private fun setupEditText() {
        edt_main.textSize = Prefs.getTextSize(ctx = applicationContext)
        edt_main.requestFocus()
    }

    private fun setupSlider() {
        val progressWithOffset = Prefs.getTextSize(applicationContext).toInt() - 12
        seekbar.setProgress(progressWithOffset, false)
    }

    private fun setupOnClickListeners() {
        seekbar.setOnSeekBarChangeListener(this)
        btn_clear.setOnClickListener { edt_main.text?.clear() }

        // Debug
//        Toast.makeText(applicationContext, "Prefs textsize: ${Prefs.getTextSize(this)}, Slider: ${Prefs.getTextSize(this).toInt()}", Toast.LENGTH_LONG).show()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    override fun onStopTrackingTouch(p0: SeekBar?) {}
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val textSizeWithOffset = progress.toFloat() + 12f

        textSizeWithOffset.let {
            edt_main.textSize = it
            Prefs.saveTextSize(ctx = applicationContext, textSize = it)
        }
    }
}
