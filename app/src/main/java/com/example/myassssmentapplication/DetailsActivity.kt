/**
 * DetailsActivity.kt
 * 
 * PURPOSE: Entity Details Display Screen Controller
 * This file manages the detailed view screen where users examine individual entity information.
 * 
 * FUNCTIONALITY:
 * - Receives entity data from DashboardActivity via Intent extras
 * - Displays detailed entity information in a card-based layout
 * - Shows property values and descriptions in formatted text views
 * - Provides navigation options (back to dashboard, logout)
 * - Handles null/missing data with fallback text
 * 
 * DATA DISPLAY:
 * - Property 1: First main attribute of the entity
 * - Property 2: Second main attribute of the entity
 * - Description: Detailed information about the entity
 * - Uses CardView for professional presentation
 * - Scrollable layout for long content
 * 
 * USER INTERACTIONS:
 * - "Back to Dashboard" button returns to DashboardActivity
 * - "Logout" button returns to MainActivity (clears session)
 * - Confirmation dialog for logout action
 * 
 * NAVIGATION:
 * - Receives data from → DashboardActivity
 * - Back button → DashboardActivity
 * - Logout → MainActivity (with activity stack clearing)
 */
package com.example.myassssmentapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {

    private lateinit var tvProperty1: TextView
    private lateinit var tvProperty2: TextView
    private lateinit var tvProperty3: TextView
    private lateinit var tvProperty4: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnBack: Button
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Initialize views
        tvProperty1 = findViewById(R.id.tvProperty1)
        tvProperty2 = findViewById(R.id.tvProperty2)
        tvProperty3 = findViewById(R.id.tvProperty3)
        tvProperty4 = findViewById(R.id.tvProperty4)
        tvDescription = findViewById(R.id.tvDescription)
        btnBack = findViewById(R.id.btnBack)
        btnLogout = findViewById(R.id.btnLogout)

        // Get data from intent
        val property1 = intent.getStringExtra("property1") ?: ""
        val property2 = intent.getStringExtra("property2") ?: ""
        val description = intent.getStringExtra("description") ?: ""

        // Set data to views - only showing Property 1 and Property 2
        tvProperty1.text = "Property 1: $property1"
        tvProperty2.text = "Property 2: $property2"
        tvDescription.text = description
        
        // Hide Property 3 and Property 4
        tvProperty3.visibility = android.view.View.GONE
        tvProperty4.visibility = android.view.View.GONE

        // Set back button click listener
        btnBack.setOnClickListener {
            finish()
        }
        
        // Set logout button click listener
        btnLogout.setOnClickListener {
            showLogoutConfirmation()
        }
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
        // Navigate back to login and clear the activity stack
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
} 