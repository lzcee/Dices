package br.edu.ifsp.scl.ads.s5.pdm.dices

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Settings(var dicesNumber: Int, var faces: Int): Parcelable