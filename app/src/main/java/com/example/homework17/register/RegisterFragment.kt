package com.example.homework17.register

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homework17.BaseFragment
import com.example.homework17.OperationViewModel
import com.example.homework17.R
import com.example.homework17.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private val viewModel: OperationViewModel by viewModels()
    private var isValid: Boolean = false
    override fun setup() {

    }

    override fun setupListeners() {
        binding.btnRegister.setOnClickListener(){
            if (fieldCheck()){
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                val email = binding.etEmail.text.toString()
                val pass = binding.etPassword.text.toString()
                // Use the Kotlin extension in the fragment-ktx artifact.
                setFragmentResult("email", bundleOf("emailKey" to email))
                setFragmentResult("pass", bundleOf("passKey" to pass))
                context?.let { it1 -> viewModel.signup(it1,email,pass) }
            }
        }
    }
    override fun bindData() {

    }

    private fun fieldCheck(): Boolean{
        if (binding.etEmail.text.isNullOrEmpty()){
            Toast.makeText(context,"Email is required!",Toast.LENGTH_LONG).show()
        }else if (binding.etEmail.text.toString() != "eve.holt@reqres.in"){
            Toast.makeText(context,"Email not valid!",Toast.LENGTH_LONG).show()
        } else if(binding.etPassword.text.isNullOrEmpty()){
            Toast.makeText(context, "Password is required", Toast.LENGTH_LONG).show()
        }else if(binding.etRePassword.text.toString() != binding.etPassword.text.toString()){
            Toast.makeText(context, "Password not match!", Toast.LENGTH_LONG).show()
        }else{
            isValid = true
        }
        return isValid
    }
}