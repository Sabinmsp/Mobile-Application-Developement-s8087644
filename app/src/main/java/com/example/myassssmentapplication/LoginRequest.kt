/**
 * LoginRequest.kt
 * 
 * PURPOSE: Login API Request Data Model
 * This file defines the data structure sent to the authentication API endpoint.
 * 
 * FUNCTIONALITY:
 * - Represents user credentials in a structured format
 * - Automatically serialized to JSON by Gson converter
 * - Type-safe parameter validation for API calls
 * - Immutable data structure (val properties)
 * 
 * DATA STRUCTURE:
 * {
 *   "username": "FirstName",
 *   "password": "sStudentID"
 * }
 * 
 * EXAMPLE USAGE:
 * val request = LoginRequest("Sabin Man", "s8087644")
 * 
 * API INTEGRATION:
 * - Used by MainActivity for login API calls
 * - Sent as request body to POST /sydney/auth
 * - Converted to JSON automatically by Retrofit + Gson
 * - Contains student credentials for authentication
 * 
 * SECURITY NOTE:
 * - Contains sensitive information (password)
 * - Should not be logged or stored permanently
 * - Used only for temporary API transmission
 */
package com.example.myassssmentapplication

data class LoginRequest(val username: String, val password: String)
