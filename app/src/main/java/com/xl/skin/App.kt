package com.xl.skin

import android.app.Application
import com.xl.skinplugin.SkinManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SkinManager.init(this)
    }
}