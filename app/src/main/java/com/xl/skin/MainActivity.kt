package com.xl.skin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xl.skinplugin.SkinManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :BaseActivity() {
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
        btn4.setOnClickListener {
            startActivity(Intent(this, SkinActivity::class.java))
        }
    }
}