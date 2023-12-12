package com.example.homework17.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homework17.BaseFragment
import com.example.homework17.OperationViewModel
import com.example.homework17.R
import com.example.homework17.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val viewModel: OperationViewModel by viewModels()
    private lateinit var email: String
    private lateinit var pass: String
    private var isValid: Boolean = false
    override fun setup() {

//        if(context?.let { getSavedCredentials(it) } != null){
//            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
//
//        }

        if (context?.let { viewModel.isRememberMeEnabled(it) } == true){
            viewModel.getSavedCredentials(requireContext())
        }else{
            setFragmentResultListener("email") { _, bundle ->
                email = bundle.getString("emailKey").toString()
                binding.etEmail.setText(email)
            }
            setFragmentResultListener("pass") { _, bundle ->
                pass = bundle.getString("passKey").toString()
                binding.etPassword.setText(email)
            }
        }
    }

    override fun setupListeners() {
        binding.btnRegister.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.btnLogin.setOnClickListener(){
            if (fieldCheck()){
                email = binding.etEmail.text.toString()
                pass = binding.etPassword.text.toString()

                if (binding.etEmail.isActivated){
                    context?.let { viewModel.saveCredentials(email, pass, binding.cbRemember.isChecked, it) }
                }
                context?.let { it1 -> viewModel.signin(it1, email, pass) }
                setFragmentResult("loginEmail", bundleOf("loginEmailKey" to email))
                findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
                d("checkSharedCreds", "${ context?.let { it1 -> viewModel.getSavedCredentials(it1) }}")
            }
        }
    }

    override fun bindData() {

    }

    private fun fieldCheck(): Boolean{
        if (binding.etEmail.text.isNullOrEmpty()){
            Toast.makeText(context,"Email is required!", Toast.LENGTH_LONG).show()
        }else if (binding.etEmail.text.toString() != "eve.holt@reqres.in"){
            Toast.makeText(context,"Email not valid!", Toast.LENGTH_LONG).show()
        } else if(binding.etPassword.text.isNullOrEmpty()){
            Toast.makeText(context, "Password is required", Toast.LENGTH_LONG).show()
        }else{
            isValid = true
        }
        return isValid
    }
}