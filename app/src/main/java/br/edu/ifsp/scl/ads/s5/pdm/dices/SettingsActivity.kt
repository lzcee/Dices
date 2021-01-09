package br.edu.ifsp.scl.ads.s5.pdm.dices

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.edu.ifsp.scl.ads.s5.pdm.dices.databinding.ActivitySettingsBinding
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    private var settings = Settings(1,6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val shared = getSharedPreferences("br.edu.ifsp.scl.ads.s5.pdm.dices_SETTINGS", Context.MODE_PRIVATE)
        settings.dicesNumber = shared.getInt(getString(R.string.saved_dices_number),1)
        settings.faces = shared.getInt(getString(R.string.saved_faces),6)
        if (settings.dicesNumber == 1) {
            diceNumber1.isChecked = true;
        } else {
            diceNumber2.isChecked = true;
        }
        dicesFacesEt.setText(settings.faces.toString())
    }

    fun saveSettings(view: View) {
        if (view.id == R.id.saveBt) {
            if (diceNumber1.isChecked) {
                settings.dicesNumber = 1
            } else if (diceNumber2.isChecked) {
                settings.dicesNumber = 2
            }

            settings.faces = dicesFacesEt.text.toString().toInt()

            val shared: SharedPreferences = getSharedPreferences("br.edu.ifsp.scl.ads.s5.pdm.dices_SETTINGS", Context.MODE_PRIVATE)

            with(shared.edit()) {
                putInt(getString(R.string.saved_dices_number), settings.dicesNumber)
                putInt(getString(R.string.saved_faces), settings.faces)
                apply()
            }

            val resultIntent = Intent()
            setResult(RESULT_OK, resultIntent)

            finish()
        }
    }

}