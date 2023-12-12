package com.example.homework17.welcome

import android.content.Context
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homework17.BaseFragment
import com.example.homework17.OperationViewModel
import com.example.homework17.R
import com.example.homework17.databinding.FragmentWelcomeBinding


class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    private val viewModel: OperationViewModel by viewModels()
    private lateinit var loginEmailResult: String
    override fun setup() {
        setFragmentResultListener("loginEmail") { _, bundle ->
            loginEmailResult = bundle.getString("loginEmailKey").toString()
            binding.edWelcomeMessage.text = "Welcome: ${loginEmailResult}"
            d("checkCredenstians", "${context?.let { viewModel.getSavedCredentials(it) }}")
        }
    }

    override fun setupListeners() {
        binding.btnLogout.setOnClickListener(){

            context?.let { it1 -> viewModel.isRememberMeEnabled(it1) }


//            context?.let { it1 -> viewModel.clearSavedCredentials(it1) }
//            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
    }

    override fun bindData() {

    }

}