package com.moving.germany

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import com.moving.germany.ui.main.MainFragment
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.github.angads25.toggle.widget.LabeledSwitch
import com.moving.germany.about.AboutFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme()
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    private fun goToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }


    private var darkMode = true

    @Override
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_tracker, menu)
        val switchItem = menu?.findItem(R.id.dark_mode_switch)
        val darkSwitch = switchItem?.actionView?.findViewById<LabeledSwitch>(R.id.labeled_switch)
        darkSwitch?.isOn = darkMode
        darkSwitch?.setOnToggledListener { toggleableView, isOn ->
            val isNightTheme =
                resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            when (isNightTheme) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    darkMode = false
                }
                Configuration.UI_MODE_NIGHT_NO -> {
                    darkMode = true
                }
            }
            nightMode(darkMode)
            val themepref: SharedPreferences =
                getSharedPreferences("userTheme", Context.MODE_PRIVATE)
            val editor = themepref.edit()
            editor.putBoolean("userTheme", darkMode)
            editor.apply()
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_us -> {
                goToFragment(AboutFragment.newInstance())
                true
            }
            android.R.id.home -> {
                super.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initTheme() {
        val themepref: SharedPreferences = getSharedPreferences("userTheme", Context.MODE_PRIVATE)
        val editor = themepref.edit()

        if (themepref.contains("userTheme")) {
            darkMode = themepref.getBoolean("userTheme", false)
        } else {
            val isNightTheme =
                resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            darkMode = isNightTheme == Configuration.UI_MODE_NIGHT_YES
            editor.putBoolean("userTheme", !true)
            editor.apply()
        }
        nightMode(darkMode)

    }

    private fun nightMode(on: Boolean) {
        if (on)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

}