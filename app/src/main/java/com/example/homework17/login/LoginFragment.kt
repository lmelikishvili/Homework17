package com.example.homework17.login

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.homework17.BaseFragment
import com.example.homework17.OperationViewModel
import com.example.homework17.R
import com.example.homework17.databinding.FragmentLoginBinding
import com.example.homework17.datastore.UserManager
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val viewModel: OperationViewModel by viewModels()
    private lateinit var userManager: UserManager
    private lateinit var email: String
    private lateinit var pass: String
    private var isValid: Boolean = false
    override fun setup() {
        userManager = context?.let { UserManager(it.applicationContext) }!!
        setFragmentResultListener("email") { _, bundle ->
            email = bundle.getString("emailKey").toString()
            binding.etEmail.setText(email)
        }
        setFragmentResultListener("pass") { _, bundle ->
            pass = bundle.getString("passKey").toString()
            binding.etPassword.setText(pass)
        }
    }

    override fun setupListeners() {
        binding.btnRegister.setOnClickListener() {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.btnLogin.setOnClickListener() {
            if (fieldCheck()) {
                email = binding.etEmail.text.toString()
                pass = binding.etPassword.text.toString()

                if (binding.cbRemember.isChecked) {
                    storeCredentials()
                }

                if (context?.let { it1 -> viewModel.signin(it1,email,pass) } == true){
                    setFragmentResult("loginEmail", bundleOf("loginEmailKey" to email))
                    findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
                }else{
                    Toast.makeText(context,"Internet access Failed!", Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    override fun bindData() {

    }

    private fun storeCredentials() {
        val email = binding.etEmail.text.toString()

        lifecycleScope.launch {
            userManager.saveUserMail(email)
            Toast.makeText(context, "User Email Saved", Toast.LENGTH_LONG).show()
        }
    }

    private fun fieldCheck(): Boolean {
        if (binding.etEmail.text.isNullOrEmpty()) {
            Toast.makeText(context, "Email is required!", Toast.LENGTH_LONG).show()
        } else if (binding.etEmail.text.toString() != "eve.holt@reqres.in") {
            Toast.makeText(context, "Email not valid!", Toast.LENGTH_LONG).show()
        } else if (binding.etPassword.text.isNullOrEmpty()) {
            Toast.makeText(context, "Password is required", Toast.LENGTH_LONG).show()
        } else {
            isValid = true
        }
        return isValid
    }
}