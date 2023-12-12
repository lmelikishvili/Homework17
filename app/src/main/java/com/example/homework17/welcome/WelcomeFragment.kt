package com.example.homework17.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.homework17.BaseFragment
import com.example.homework17.R
import com.example.homework17.databinding.FragmentWelcomeBinding


class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    private lateinit var loginEmailResult: String
    override fun setup() {
        setFragmentResultListener("loginEmail") { _, bundle ->
            loginEmailResult = bundle.getString("loginEmailKey").toString()
            binding.edWelcomeMessage.text = "Welcome: ${loginEmailResult}"
        }
    }

    override fun setupListeners() {
        binding.btnLogout.setOnClickListener(){
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
    }

    override fun bindData() {

    }

}