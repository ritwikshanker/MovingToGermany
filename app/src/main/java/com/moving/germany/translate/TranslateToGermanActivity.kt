package com.moving.germany.translate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moving.germany.R
import com.moving.germany.ui.main.MainFragment

class TranslateToGermanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.translate_to_german_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TranslateToGermanFragment.newInstance())
                .commitNow()
        }
    }
}