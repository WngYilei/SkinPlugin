package com.xl.skin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xl.skinplugin.SkinManager

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        SkinManager.initCreate(this)
        super.onCreate(savedInstanceState)
    }
}