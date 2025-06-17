/**
 * EndpointTester.kt
 * 
 * PURPOSE: Development/Debug Helper for API Endpoint Testing
 * This file provides utilities to test different API endpoints during development.
 * 
 * FUNCTIONALITY:
 * - Tests multiple API endpoint variations automatically
 * - Provides detailed logging of API response codes and errors
 * - Helps identify correct endpoint paths for different environments
 * - Supports parallel testing of multiple endpoints
 * - Returns working endpoint information via callback
 * 
 * ENDPOINTS TESTED:
 * - /auth - Simple authentication endpoint
 * - /sydney/auth - Sydney campus specific endpoint
 * - /footscray/auth - Footscray campus specific endpoint
 * - /login - Alternative login endpoint
 * 
 * USAGE SCENARIOS:
 * - Debugging login issues
 * - Finding correct campus-specific endpoints
 * - Testing API connectivity
 * - Development and troubleshooting
 * 
 * DEVELOPMENT TOOL:
 * - Used during development phase
 * - Can be removed in production builds
 * - Provides detailed debugging information
 * - Helps resolve API integration issues
 * 
 * CALLBACK PATTERN:
 * - Tests all endpoints asynchronously
 * - Returns first successful endpoint name
 * - Provides null if no endpoints work
 * - Includes detailed logging for analysis
 * 
 * NOTE: This is a utility class for development purposes only.
 */
package com.example.myassssmentapplication

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

object EndpointTester {
    
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://nit3213api.onrender.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    interface TestApiService {
        @POST("auth")
        fun loginAuth(@Body request: LoginRequest): Call<LoginResponse>
        
        @POST("sydney/auth")
        fun loginSydney(@Body request: LoginRequest): Call<LoginResponse>
        
        @POST("footscray/auth")
        fun loginFootscray(@Body request: LoginRequest): Call<LoginResponse>
        
        @POST("login")
        fun loginSimple(@Body request: LoginRequest): Call<LoginResponse>
    }
    
    private val testApi = retrofit.create(TestApiService::class.java)
    
    fun testEndpoints(username: String, password: String, callback: (String?) -> Unit) {
        val request = LoginRequest(username, password)
        val endpoints = listOf(
            "auth" to testApi.loginAuth(request),
            "sydney" to testApi.loginSydney(request),
            "footscray" to testApi.loginFootscray(request),
            "login" to testApi.loginSimple(request)
        )
        
        var completedTests = 0
        var workingEndpoint: String? = null
        
        endpoints.forEach { (endpointName, call) ->
            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    completedTests++
                    if (response.isSuccessful && workingEndpoint == null) {
                        workingEndpoint = endpointName
                        Log.d("EndpointTester", "Working endpoint found: $endpointName")
                    } else {
                        Log.d("EndpointTester", "$endpointName failed with code: ${response.code()}")
                    }
                    
                    if (completedTests == endpoints.size) {
                        callback(workingEndpoint)
                    }
                }
                
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    completedTests++
                    Log.d("EndpointTester", "$endpointName network error: ${t.message}")
                    if (completedTests == endpoints.size) {
                        callback(workingEndpoint)
                    }
                }
            })
        }
    }
} 