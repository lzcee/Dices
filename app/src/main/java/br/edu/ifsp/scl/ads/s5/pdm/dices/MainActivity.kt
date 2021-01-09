package br.edu.ifsp.scl.ads.s5.pdm.dices

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.s5.pdm.dices.databinding.ActivityMainBinding
import java.util.*
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private val SETTINGS_REQUEST_CODE = 1
    private var settings =
        Settings(1, 6)

    private fun openSettingsActivity() {
        val settingsIntent = Intent("SETTINGS")
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
        if (requestCode == SETTINGS_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            val shared = getSharedPreferences("br.edu.ifsp.scl.ads.s5.pdm.dices_SETTINGS", Context.MODE_PRIVATE)
            settings.dicesNumber = shared.getInt(getString(R.string.saved_dices_number),1)
            settings.faces = shared.getInt(getString(R.string.saved_faces),6)

            if (settings.dicesNumber == 2) {
                findViewById<View>(R.id.resultado2Iv).visibility = View.VISIBLE
            } else {
                findViewById<View>(R.id.resultado2Iv).visibility = View.GONE
                findViewById<View>(R.id.resultadoIv).visibility = View.VISIBLE
            }
        }
    }

    fun onClick(view: View) {
        if (view == activityMainBinding.jogarBt) {
            for (i in 1..settings.dicesNumber) {
                val resultado: Int = Random(System.currentTimeMillis()).nextInt(settings.faces) + 1
                val resultadoImagem = "dice_$resultado"

                if (i == 1) {
                    activityMainBinding.resultadoIv.setImageResource(
                            resources.getIdentifier(resultadoImagem, "drawable", packageName)
                    )
                    activityMainBinding.resultadoTv.text = resultado.toString()
                } else {
                    activityMainBinding.resultado2Iv.setImageResource(
                            resources.getIdentifier(resultadoImagem, "drawable", packageName)
                    )
                    activityMainBinding.resultadoTv.text = activityMainBinding.resultadoTv.text.toString() + " "+ resultado.toString()
                }
            }

        }
    }
}