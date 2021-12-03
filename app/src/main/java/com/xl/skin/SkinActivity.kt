package com.xl.skin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xl.skin.ui.main.SkinFragment

class SkinActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.skin_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SkinFragment.newInstance())
                .commitNow()
        }
    }
}