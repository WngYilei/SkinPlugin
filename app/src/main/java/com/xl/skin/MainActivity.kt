package com.xl.skin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xl.skinplugin.SkinManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1.setOnClickListener {
            SkinManager.loadSKin("")

        }

        btn2.setOnClickListener {

            val skinPackageName = "/skinpackage-debug.apk"
            val skinPkg = baseContext.cacheDir.toString() + skinPackageName
            SkinManager.loadSKin(skinPkg)
        }

        btn3.setOnClickListener {
            val skinPackageName = "/skinpkg-debug2.apk"
            val skinPkg = baseContext.cacheDir.toString() + skinPackageName
            SkinManager.loadSKin(skinPkg)
        }
    }
}