/**
 * LoginResponse.kt
 * 
 * PURPOSE: Login API Response Data Model
 * This file defines the data structure received from the authentication API endpoint.
 * 
 * FUNCTIONALITY:
 * - Represents authentication response from the server
 * - Automatically deserialized from JSON by Gson converter
 * - Type-safe access to authentication token
 * - Immutable data structure (val properties)
 * 
 * DATA STRUCTURE:
 * {
 *   "keypass": "TopicName"
 * }
 * 
 * KEYPASS USAGE:
 * - Unique identifier for authenticated session
 * - Used for subsequent API calls (dashboard data)
 * - Acts as temporary access token
 * - Required for accessing protected endpoints
 * 
 * API INTEGRATION:
 * - Received from POST /sydney/auth endpoint
 * - Processed by MainActivity after successful login
 * - Passed to DashboardActivity for data retrieval
 * - Used in GET /dashboard/{keypass} API calls
 * 
 * FLOW:
 * Login → Server validates → Returns keypass → Use for dashboard access
 */
package com.example.myassssmentapplication

data class LoginResponse(val keypass: String)
