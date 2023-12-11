package com.example.homework17.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.homework17.BaseFragment
import com.example.homework17.R
import com.example.homework17.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override fun setup() {

    }

    override fun setupListeners() {
        binding.btnRegister.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.btnLogin.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }
    }

    override fun bindData() {

    }

}