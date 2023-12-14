package com.example.homework17.welcome

import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.homework17.BaseFragment
import com.example.homework17.OperationViewModel
import com.example.homework17.R
import com.example.homework17.databinding.FragmentWelcomeBinding
import com.example.homework17.datastore.UserManager
import kotlinx.coroutines.launch


class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    private val viewModel: OperationViewModel by viewModels()
    private lateinit var userManager: UserManager
    private lateinit var loginEmailResult: String
    override fun setup() {
        userManager = context?.let { UserManager(it) }!!
        setFragmentResultListener("loginEmail") { _, bundle ->
            loginEmailResult = bundle.getString("loginEmailKey").toString()
            binding.edWelcomeMessage.text = "Welcome: ${loginEmailResult}"
        }
    }

    override fun setupListeners() {
        binding.btnLogout.setOnClickListener() {
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
            clearCredentials()
        }
    }

    override fun bindData() {

    }

    private fun clearCredentials() {
        lifecycleScope.launch {
            context?.let { userManager.clearDataStore(it) }
        }
    }

}