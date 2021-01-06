package br.edu.ifsp.scl.ads.s5.pdm.dices

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    private lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        settings = intent.getParcelableExtra(MainActivity.EXTRA_SETTINGS) ?: Settings(1, 6)
    }

    fun saveSettings(view: View) {
        if (view.id == R.id.saveBt) {
            if (diceNumber1.isChecked) {
                settings.dicesNumber = 1
            } else if (diceNumber2.isChecked) {
                settings.dicesNumber = 2
            }

            settings.faces = dicesFacesEt.text.toString().toInt()

            val resultIntent = Intent()
            resultIntent.putExtra(MainActivity.EXTRA_SETTINGS, settings)
            setResult(RESULT_OK, resultIntent)

            finish()
        }
    }

}