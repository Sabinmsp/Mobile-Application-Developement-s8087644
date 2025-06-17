/**
 * MainActivity.kt
 * 
 * PURPOSE: Login Screen Controller
 * This file manages the user authentication screen where students log in with their credentials.
 * 
 * FUNCTIONALITY:
 * - Displays login form with username and password
 * - Validates user input (checks for empty fields)
 * - Makes API call to authenticate user credentials
 * - Processes login response and handles success/failure scenarios
 * - Navigates to Dashboard screen upon successful authentication
 * - Provides user feedback through error messages and loading states
 * 
 * API INTEGRATION:
 * - Connects to: https://nit3213api.onrender.com/sydney/auth
 * - Sends: LoginRequest with username and password
 * - Receives: LoginResponse with keypass for authenticated session
 * 
 * NAVIGATION:
 * - Entry point of the application
 * - On successful login → DashboardActivity
 * - Handles activity stack management for security
 */
package com.example.myassssmentapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvResult = findViewById(R.id.tvResult)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                showError("Please fill all fields")
                return@setOnClickListener
            }

            // Disable button during request
            btnLogin.isEnabled = false
            btnLogin.text = "Logging in..."
            
            val request = LoginRequest(username, password)

            // Attempt login with fixed endpoint
            attemptLogin(request, username)
        }
    }

    private fun attemptLogin(request: LoginRequest, username: String) {
        Log.d("MainActivity", "Attempting login with username: '$username'")
        
        RetrofitClient.instance.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                btnLogin.isEnabled = true
                btnLogin.text = "LOGIN"
                
                if (response.isSuccessful) {
                    val keypass = response.body()?.keypass
                    if (keypass != null) {
                        Log.d("MainActivity", "Login successful, keypass: $keypass")
                        // Navigate to Dashboard
                        val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                        intent.putExtra("keypass", keypass)
                        intent.putExtra("username", username)
                        startActivity(intent)
                        finish() // Clear login activity from stack
                    } else {
                        showError("Login successful but no keypass received")
                    }
                } else {
                    val errorMsg = when (response.code()) {
                        401 -> "Invalid credentials. Check your first name and student ID format (s12345678)"
                        404 -> "API endpoint not found. Please try again later"
                        500 -> "Server error. Please try again later"
                        else -> "Login failed (${response.code()}). Please check your credentials."
                    }
                    showError(errorMsg)
                    Log.e("MainActivity", "Login failed with code: ${response.code()}, message: ${response.message()}")
                    Log.e("MainActivity", "Request: username='$username', password='${request.password}'")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                btnLogin.isEnabled = true
                btnLogin.text = "LOGIN"
                
                val errorMessage = when {
                    t.message?.contains("timeout", ignoreCase = true) == true -> {
                        "Connection timeout. The server might be slow. Please try again."
                    }
                    t.message?.contains("Unable to resolve host", ignoreCase = true) == true -> {
                        "Network connection error. Check your internet connection and try again."
                    }
                    t.message?.contains("ConnectException", ignoreCase = true) == true -> {
                        "Cannot connect to server. Please check your internet connection."
                    }
                    else -> "Network error: ${t.message}"
                }
                
                showError(errorMessage)
                Log.e("MainActivity", "Network error details: ${t.javaClass.simpleName}: ${t.message}", t)
            }
        })
    }
    
    private fun showError(message: String) {
        tvResult.text = message
        tvResult.visibility = android.view.View.VISIBLE
    }
}
