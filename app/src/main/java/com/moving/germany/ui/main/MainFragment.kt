package com.moving.germany.ui.main

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
import java.util.Locale

class MainFragment : BaseFragment<MainFragmentBinding>(), TextToSpeech.OnInitListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var tts: TextToSpeech? = null
    private lateinit var viewModel: MainViewModel

    override fun layoutResourceId(): Int = R.layout.main_fragment

    override fun initViewCreated() {
        initTTS()
        initClickListeners()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val apiKey: String = BuildConfig.apiKey
    }

    private fun initClickListeners() {
        binding.buttonSpeak.setOnClickListener {
            speakOut()
        }
    }

    private fun initTTS() {
        tts = TextToSpeech(requireContext(), this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            } else {
                binding.buttonSpeak.isEnabled = true
            }

        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }

    private fun speakOut() {
        val text = binding.textField.editText?.text.toString()
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}