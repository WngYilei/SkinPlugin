package com.xl.skinplugin.utils

import android.content.Context
import android.content.SharedPreferences

object SkinCache {

    private const val SKIN_SHARED = "skins"
    private const val KEY_SKIN_PATH = "skin-path"

    private val mPref: SharedPreferences =
        XApp.getApp().getSharedPreferences(SKIN_SHARED, Context.MODE_PRIVATE)

    fun reset() {
        mPref.edit().remove(KEY_SKIN_PATH).apply()
    }


    fun setSkinPath(path: String) {
        mPref.edit().putString(KEY_SKIN_PATH, path).apply()
    }

    fun getSkinPath() = mPref.getString(KEY_SKIN_PATH, "")
}