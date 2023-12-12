package com.example.homework17

import android.content.Context
import android.util.Log.d
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.homework17.login.SignInBody
import com.example.homework17.network.ApiInterface
import com.example.homework17.network.RetrofitInstance
import com.example.homework17.register.UserBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OperationViewModel:ViewModel()

{
    fun signin(context: Context, email: String, password: String){
        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val signInInfo = SignInBody(email, password)
        retIn.signin(signInInfo).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    context,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {
                    Toast.makeText(context, "Login success!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Login failed!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun signup(context: Context, email: String, password: String){
        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val registerInfo = UserBody(email,password)

        retIn.registerUser(registerInfo).enqueue(object :
            Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    context,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {
                    Toast.makeText(context, "Register Success!", Toast.LENGTH_SHORT)
                        .show()
                }
                else{
                    Toast.makeText(context, "Registration failed!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    fun saveCredentials(username: String, password: String, rememberMe: Boolean, context: Context) {
        val sharedPreferences = context.getSharedPreferences("loginPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("username", username)
        editor.putString("password", password)
        editor.putBoolean("rememberMe", rememberMe)

        editor.apply()
    }

    fun isRememberMeEnabled(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences("loginPreferences", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("rememberMe", false)
    }

    fun getSavedCredentials(context: Context): Pair<String, String>? {
        val sharedPreferences = context.getSharedPreferences("loginPreferences", Context.MODE_PRIVATE)

        val email = sharedPreferences.getString("username", null).toString()
        val pass = sharedPreferences.getString("password", null).toString()

        if (email != null && pass != null) {
            return Pair(email, pass)
        }

        return null
    }

    fun clearSavedCredentials(context: Context) {
        val sharedPreferences = context.getSharedPreferences("loginPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.remove("username")
        editor.remove("password")
        editor.remove("rememberMe")

        editor.apply()
    }
}