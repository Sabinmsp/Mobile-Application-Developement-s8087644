/**
 * RetrofitClient.kt
 * 
 * PURPOSE: API Connection Manager (Singleton)
 * This file configures and provides a centralized HTTP client for all API communications.
 * 
 * FUNCTIONALITY:
 * - Configures Retrofit HTTP client with base URL
 * - Sets up JSON converter for automatic serialization/deserialization
 * - Provides singleton instance for efficient resource usage
 * - Creates ApiService interface implementation
 * - Manages HTTP client configuration (timeouts, interceptors, etc.)
 * 
 * CONFIGURATION:
 * - Base URL: https://nit3213api.onrender.com/
 * - Converter: Gson for JSON handling
 * - Pattern: Singleton (lazy initialization)
 * - Thread-safe: Yes (by lazy delegate)
 * - Timeouts: Connect(120s), Read(120s), Write(120s)
 * - Retry: Automatic retry on network failures
 * 
 * USAGE:
 * - RetrofitClient.instance.login() for authentication
 * - RetrofitClient.instance.getDashboard() for data retrieval
 * - Used by MainActivity and DashboardActivity
 * 
 * ARCHITECTURE ROLE:
 * - Separates network configuration from business logic
 * - Provides consistent API access across the application
 * - Makes API endpoint changes manageable from single location
 */
package com.example.myassssmentapplication

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "https://nit3213api.onrender.com/"

    val instance: ApiService by lazy {
        // Create logging interceptor for debugging
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Create OkHttp client with extended timeouts and retry logic
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .retryOnConnectionFailure(true)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}
