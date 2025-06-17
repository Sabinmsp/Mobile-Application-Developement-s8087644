/**
 * ApiService.kt
 * 
 * PURPOSE: API Endpoint Interface Definition
 * This file defines all available API endpoints and their request/response contracts.
 * 
 * FUNCTIONALITY:
 * - Declares API endpoint methods with HTTP annotations
 * - Specifies request and response data types
 * - Defines URL paths and HTTP methods (GET, POST, etc.)
 * - Provides type-safe API method signatures
 * - Works with Retrofit to auto-generate implementation
 * 
 * ENDPOINTS DEFINED:
 * 1. POST /sydney/auth - User authentication
 *    - Input: LoginRequest (username, password)
 *    - Output: LoginResponse (keypass)
 * 
 * 2. GET /dashboard/{keypass} - Dashboard data retrieval
 *    - Input: keypass as path parameter
 *    - Output: DashboardResponse (entities list, total count)
 * 
 * RETROFIT ANNOTATIONS:
 * - @POST, @GET: HTTP method specifications
 * - @Body: Request body parameter
 * - @Path: URL path parameter replacement
 * 
 * USAGE:
 * - Implemented automatically by Retrofit
 * - Called via RetrofitClient.instance
 * - Returns Call<T> objects for asynchronous execution
 */
package com.example.myassssmentapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("sydney/auth") // Fixed endpoint
    fun login(@Body request: LoginRequest): Call<LoginResponse>
    
    @GET("dashboard/{keypass}")
    fun getDashboard(@Path("keypass") keypass: String): Call<DashboardResponse>
}
