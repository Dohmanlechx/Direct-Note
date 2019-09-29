package com.dohman.directnote

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    private var hasEditTextBeenInit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Layout default is Dark Mode colored
        if (!Prefs.isDarkModeChosen(applicationContext)) setViewsAtLightMode()

        edt_main.post { setupEditText() }
        seekbar.post { setupSlider() }
        setupOnClickListeners()
    }

    override fun onResume() {
        super.onResume()
        if (hasEditTextBeenInit) edt_main.requestFocus()
    }

    override fun onPause() {
        super.onPause()
        edt_main.clearFocus()
    }

    private fun setupEditText() {
        edt_main.textSize = Prefs.getTextSize(ctx = applicationContext)
        edt_main.requestFocus()
        hasEditTextBeenInit = true
    }

    private fun setupSlider() {
        val progressWithOffset = Prefs.getTextSize(applicationContext).toInt() - 12
        seekbar.setProgress(progressWithOffset, false)
    }

    private fun setupOnClickListeners() {
        seekbar.setOnSeekBarChangeListener(this)
        btn_clear.setOnClickListener { edt_main.text?.clear() }
        btn_dark_mode.setOnClickListener { btnDarkModeAction() }
    }

    private fun btnDarkModeAction() {
        val isGoingIntoDarkMode = !Prefs.isDarkModeChosen(applicationContext)
        if (isGoingIntoDarkMode) setViewsAtDarkMode() else setViewsAtLightMode()
        saveDarkModeValue(isDarkMode = isGoingIntoDarkMode)
    }

    private fun saveDarkModeValue(isDarkMode: Boolean) =
        Prefs.saveDarkModeValue(applicationContext, isDarkMode)

    private fun setViewsAtDarkMode() = dryTheWholeLayout(
        backgroundColor = R.color.colorPrimaryDark,
        accentColor = R.color.colorAccent,
        btnClearDrawable = R.drawable.ic_clear_darkmode,
        btnDarkModeDrawable = R.drawable.ic_brightness_darkmode
    )

    private fun setViewsAtLightMode() = dryTheWholeLayout(
        backgroundColor = R.color.colorAccent,
        accentColor = R.color.colorPrimaryDark,
        btnClearDrawable = R.drawable.ic_clear_lightmode,
        btnDarkModeDrawable = R.drawable.ic_brightness_lightmode
    )

    private fun dryTheWholeLayout(
        backgroundColor: Int,
        accentColor: Int,
        btnClearDrawable: Int,
        btnDarkModeDrawable: Int
    ) {
        background.setBackgroundColor(getColor(backgroundColor))

        btn_clear.setBackgroundResource(btnClearDrawable)
        btn_dark_mode.setBackgroundResource(btnDarkModeDrawable)

        accentColor.let {
            edt_main.setTextColor(getColor(it))
            seekbar.progressDrawable.setApiColorFilter(ContextCompat.getColor(applicationContext, it), Mode.MULTIPLY)
            seekbar.thumb.setApiColorFilter(ContextCompat.getColor(applicationContext, it), Mode.SRC_ATOP)
        }
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