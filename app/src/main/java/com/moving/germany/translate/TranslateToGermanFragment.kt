package com.moving.germany.translate

import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.moving.germany.BuildConfig
import com.moving.germany.R
import com.moving.germany.base.BaseFragment
import com.moving.germany.databinding.TranslateToGermanFragmentBinding
import com.moving.germany.ui.main.MainViewModel
import java.util.Locale

class TranslateToGermanFragment : BaseFragment<TranslateToGermanFragmentBinding>(),
    TextToSpeech.OnInitListener {
    companion object {
        fun newInstance() = TranslateToGermanFragment()
    }

    private var tts: TextToSpeech? = null
    private lateinit var viewModel: TranslateViewModel

    override fun layoutResourceId(): Int = R.layout.translate_to_german_fragment

    override fun initViewCreated() {
        initTTS()
        initClickListeners()
        viewModel = ViewModelProvider(this)[TranslateViewModel::class.java]
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