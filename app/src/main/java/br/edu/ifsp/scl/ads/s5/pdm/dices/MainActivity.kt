package br.edu.ifsp.scl.ads.s5.pdm.dices

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.s5.pdm.dices.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private val SETTINGS_REQUEST_CODE = 1
    companion object {
        const val EXTRA_SETTINGS = "EXTRA_SETTINGS"
    }
    private var settings =
        Settings(1,6)

    fun openSettingsActivity() {
        val settingsIntent = Intent("SETTINGS")
        settingsIntent.putExtra(EXTRA_SETTINGS, settings)
        startActivityForResult(settingsIntent, SETTINGS_REQUEST_CODE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settingsMi -> {
                openSettingsActivity()
                true
            }
            R.id.exitMi -> {
                finish()
                true
            }
            else -> false
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        @Nullable data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SETTINGS_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            settings = data.getParcelableExtra(EXTRA_SETTINGS)
            if (settings.dicesNumber == 2) {
                findViewById<View>(R.id.resultado2Iv).visibility = View.VISIBLE
            } else {
                findViewById<View>(R.id.resultado2Iv).visibility = View.GONE
            }
        }
    }

    fun onClick(view: View) {
        if (view == activityMainBinding.jogarBt) {
            val resultado: Int = Random(System.currentTimeMillis()).nextInt(6) + 1;
            activityMainBinding.resultadoTv.text = resultado.toString()
            // Gerando nome da imagem
            val resultadoImagem = "dice_$resultado"
            activityMainBinding.resultadoIv.setImageResource(
                resources.getIdentifier(resultadoImagem, "drawable", packageName)
            )
        }
    }
}