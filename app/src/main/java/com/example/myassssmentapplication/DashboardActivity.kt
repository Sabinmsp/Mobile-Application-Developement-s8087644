/**
 * DashboardActivity.kt
 * 
 * PURPOSE: Main Data Display Screen Controller
 * This file manages the dashboard where authenticated users view their assigned entities/data.
 * 
 * FUNCTIONALITY:
 * - Receives keypass and username from successful login
 * - Makes API call to fetch user's dashboard data
 * - Displays list of entities in a scrollable RecyclerView
 * - Shows total count of available entities
 * - Handles user interactions (item clicks, logout)
 * - Provides loading indicators and error handling
 * - Shows personalized welcome message for the logged-in student
 * 
 * DATA DISPLAY:
 * - Uses custom EntityRecyclerAdapter to format list items
 * - Shows entity properties in "Property1 - Property2" format
 * - Displays entity descriptions as secondary text
 * - Handles null/missing data gracefully
 * 
 * API INTEGRATION:
 * - Connects to: https://nit3213api.onrender.com/dashboard/{keypass}
 * - Receives: DashboardResponse with entities list and total count
 * - Logs API responses for debugging purposes
 * 
 * NAVIGATION:
 * - Item click → DetailsActivity (passes entity data)
 * - Logout button → MainActivity (clears session)
 * - Back button → Previous screen
 */
package com.example.myassssmentapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvWelcome: TextView
    private lateinit var tvTotal: TextView
    private lateinit var rvEntities: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnLogout: Button
    private var entities: List<Entity> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize views
        tvTitle = findViewById(R.id.tvTitle)
        tvWelcome = findViewById(R.id.tvWelcome)
        tvTotal = findViewById(R.id.tvTotal)
        rvEntities = findViewById(R.id.rvEntities)
        progressBar = findViewById(R.id.progressBar)
        btnLogout = findViewById(R.id.btnLogout)
        
        // Set up RecyclerView with LinearLayoutManager
        rvEntities.layoutManager = LinearLayoutManager(this)

        // Get data from intent
        val keypass = intent.getStringExtra("keypass")
        val username = intent.getStringExtra("username")
        
        if (keypass == null) {
            Toast.makeText(this, "Error: No keypass received", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Display dashboard title
        tvTitle.text = "Dashboard"
        
        if (username != null) {
            tvWelcome.text = "Welcome, $username!"
            tvWelcome.visibility = android.view.View.VISIBLE
        } else {
            tvWelcome.visibility = android.view.View.GONE
        }
        
        // Set up logout button
        btnLogout.setOnClickListener {
            showLogoutConfirmation()
        }
        
        // Show loading
        progressBar.visibility = android.view.View.VISIBLE
        
        // Fetch dashboard data
        fetchDashboardData(keypass)
    }

    private fun showLogoutConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                logout()
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun logout() {
        // Clear any stored data if needed
        // For now, just navigate back to login and clear the activity stack
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun fetchDashboardData(keypass: String) {
        RetrofitClient.instance.getDashboard(keypass).enqueue(object : Callback<DashboardResponse> {
            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                progressBar.visibility = android.view.View.GONE
                
                if (response.isSuccessful) {
                    val dashboardResponse = response.body()
                    if (dashboardResponse != null) {
                        Log.d("DashboardActivity", "API Response: $dashboardResponse")
                        entities = dashboardResponse.entities
                        tvTotal.text = "Your Entities: ${dashboardResponse.entityTotal}"
                        
                        // Log each entity to show ACTUAL field names and values (no guessing!)
                        entities.forEachIndexed { index, entity ->
                            Log.d("DashboardActivity", "=== Entity $index - ALL ACTUAL FIELDS ===")
                            
                            val actualFields = entity.getAllActualFields()
                            if (actualFields.isEmpty()) {
                                Log.d("DashboardActivity", "  ❌ No fields found in this entity")
                            } else {
                                actualFields.forEach { (fieldName, value) ->
                                    Log.d("DashboardActivity", "  ✅ $fieldName: '$value'")
                                }
                            }
                            
                            Log.d("DashboardActivity", "=== Entity $index - WHAT APP WILL SHOW ===")
                            Log.d("DashboardActivity", "  🏷️ Label 1: '${entity.displayPropertyName1}' = '${entity.displayProperty1}'")
                            Log.d("DashboardActivity", "  🏷️ Label 2: '${entity.displayPropertyName2}' = '${entity.displayProperty2}'")
                            Log.d("DashboardActivity", "  📝 Description: '${entity.displayDescription}'")
                            Log.d("DashboardActivity", "==========================================")
                        }
                        
                        // Create custom adapter for RecyclerView
                        val adapter = EntityRecyclerAdapter(entities) { selectedEntity ->
                            // Handle item click - navigate to Details screen (only Property 1, 2, and Description)
                            val intent = Intent(this@DashboardActivity, DetailsActivity::class.java)
                            intent.putExtra("property1", selectedEntity.displayProperty1)
                            intent.putExtra("property2", selectedEntity.displayProperty2)
                            intent.putExtra("description", selectedEntity.displayDescription)
                            startActivity(intent)
                        }
                        rvEntities.adapter = adapter
                    }
                } else {
                    Toast.makeText(this@DashboardActivity, "Failed to load dashboard data", Toast.LENGTH_SHORT).show()
                    Log.e("DashboardActivity", "Response error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                progressBar.visibility = android.view.View.GONE
                
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
                
                Toast.makeText(this@DashboardActivity, errorMessage, Toast.LENGTH_LONG).show()
                Log.e("DashboardActivity", "Network error details: ${t.javaClass.simpleName}: ${t.message}", t)
                
                // Show retry option
                AlertDialog.Builder(this@DashboardActivity)
                    .setTitle("Connection Error")
                    .setMessage("$errorMessage\n\nWould you like to retry?")
                    .setPositiveButton("Retry") { _, _ ->
                        val retryKeypass = intent.getStringExtra("keypass")
                        if (retryKeypass != null) {
                            progressBar.visibility = android.view.View.VISIBLE
                            fetchDashboardData(retryKeypass)
                        }
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        })
    }
} 