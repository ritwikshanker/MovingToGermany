package com.moving.germany.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.moving.germany.BuildConfig
import com.moving.germany.R
import com.moving.germany.base.BaseFragment
import com.moving.germany.databinding.MainFragmentBinding

class MainFragment : BaseFragment<MainFragmentBinding>() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun layoutResourceId(): Int = R.layout.main_fragment

    override fun initViewCreated() {
        binding.message.text = "Hello"
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val apiKey: String = BuildConfig.apiKey
        Toast.makeText(requireContext(), apiKey, Toast.LENGTH_LONG).show()
    }

}