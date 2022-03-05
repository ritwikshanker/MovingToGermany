package com.moving.germany.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.moving.germany.BuildConfig
import com.moving.germany.R
import com.moving.germany.base.BaseFragment
import com.moving.germany.databinding.MainFragmentBinding
import com.moving.germany.translate.TranslateToGermanActivity
import java.util.Locale

class MainFragment : BaseFragment<MainFragmentBinding>() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun layoutResourceId(): Int = R.layout.main_fragment

    override fun initViewCreated() {
        initClickListeners()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val apiKey: String = BuildConfig.apiKey
    }

    private fun initClickListeners() {
        binding.buttonSpeak.setOnClickListener {
            val intent = Intent(context, TranslateToGermanActivity::class.java)
            startActivity(intent)
        }
    }
}