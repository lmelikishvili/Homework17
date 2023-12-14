package com.example.homework17

import android.util.Log.d
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.homework17.databinding.FragmentSplashBinding
import com.example.homework17.datastore.UserManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private lateinit var userManager: UserManager
    override fun setup() {
        userManager = context?.let { UserManager(it.applicationContext) }!!
        getUserData()

    }

    override fun setupListeners() {

    }

    override fun bindData() {

    }

    private fun getUserData(){
        userManager.userEmaiFlow.asLiveData().observe(this){email->
            email?.let {
                if (email.isNullOrEmpty()){
                    goToLogin()
                }else{
                    goToWelcome()
                }
                d("getUser", "${email}")
            }
        }
    }

    private fun goToLogin(){
        lifecycleScope.launch {
            delay(1000)
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }

    private fun goToWelcome(){
        lifecycleScope.launch {
            delay(1000)
            findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
        }
    }
}