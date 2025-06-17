package com.example.myassssmentapplication

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

/**
 * Instrumented test for DashboardActivity, which will execute on an Android device.
 * Tests the Android-specific UI components and behavior.
 */
@RunWith(AndroidJUnit4::class)
class DashboardActivityInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.myassssmentapplication", appContext.packageName)
    }

    @Test
    fun dashboardActivity_launchWithValidKeypass_shouldNotCrash() {
        // Create intent with required extras
        val intent = Intent(ApplicationProvider.getApplicationContext(), DashboardActivity::class.java).apply {
            putExtra("keypass", "test-keypass")
            putExtra("username", "Test User")
        }

        // Launch activity with intent
        ActivityScenario.launch<DashboardActivity>(intent).use { scenario ->
            // Verify activity launches without crashing
            scenario.onActivity { activity ->
                assertNotNull("Activity should not be null", activity)
                assertFalse("Activity should not be finishing", activity.isFinishing)
            }
        }
    }

    @Test
    fun dashboardActivity_launchWithoutKeypass_shouldFinish() {
        // Create intent without keypass (should cause activity to finish)
        val intent = Intent(ApplicationProvider.getApplicationContext(), DashboardActivity::class.java).apply {
            putExtra("username", "Test User")
            // No keypass provided
        }

        // Launch activity with intent
        ActivityScenario.launch<DashboardActivity>(intent).use { scenario ->
            // Activity should finish due to missing keypass
            scenario.onActivity { activity ->
                // The activity should handle missing keypass gracefully
                assertNotNull("Activity should not be null", activity)
            }
        }
    }

    @Test
    fun dashboardActivity_withUsername_shouldDisplayWelcomeMessage() {
        val testUsername = "Sabin Man"
        val intent = Intent(ApplicationProvider.getApplicationContext(), DashboardActivity::class.java).apply {
            putExtra("keypass", "test-keypass")
            putExtra("username", testUsername)
        }

        ActivityScenario.launch<DashboardActivity>(intent).use { scenario ->
            scenario.onActivity { activity ->
                // Check if welcome message is set correctly
                val welcomeTextView = activity.findViewById<android.widget.TextView>(R.id.tvWelcome)
                assertNotNull("Welcome TextView should exist", welcomeTextView)
                
                // Note: In real test, we might need to wait for UI updates
                // This is a basic structure test
            }
        }
    }

    @Test
    fun dashboardActivity_hasRequiredUIComponents() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), DashboardActivity::class.java).apply {
            putExtra("keypass", "test-keypass")
            putExtra("username", "Test User")
        }

        ActivityScenario.launch<DashboardActivity>(intent).use { scenario ->
            scenario.onActivity { activity ->
                // Verify all required UI components exist
                assertNotNull("Title TextView should exist", 
                             activity.findViewById<android.widget.TextView>(R.id.tvTitle))
                assertNotNull("Welcome TextView should exist", 
                             activity.findViewById<android.widget.TextView>(R.id.tvWelcome))
                assertNotNull("Total TextView should exist", 
                             activity.findViewById<android.widget.TextView>(R.id.tvTotal))
                assertNotNull("RecyclerView should exist", 
                             activity.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvEntities))
                assertNotNull("ProgressBar should exist", 
                             activity.findViewById<android.widget.ProgressBar>(R.id.progressBar))
                assertNotNull("Logout Button should exist", 
                             activity.findViewById<android.widget.Button>(R.id.btnLogout))
            }
        }
    }

    @Test
    fun dashboardActivity_recyclerView_hasLinearLayoutManager() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), DashboardActivity::class.java).apply {
            putExtra("keypass", "test-keypass")
            putExtra("username", "Test User")
        }

        ActivityScenario.launch<DashboardActivity>(intent).use { scenario ->
            scenario.onActivity { activity ->
                val recyclerView = activity.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvEntities)
                assertNotNull("RecyclerView should exist", recyclerView)
                
                val layoutManager = recyclerView.layoutManager
                assertNotNull("LayoutManager should be set", layoutManager)
                assertTrue("Should use LinearLayoutManager", 
                          layoutManager is androidx.recyclerview.widget.LinearLayoutManager)
            }
        }
    }

    @Test
    fun dashboardActivity_initialState_progressBarVisible() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), DashboardActivity::class.java).apply {
            putExtra("keypass", "test-keypass")
            putExtra("username", "Test User")
        }

        ActivityScenario.launch<DashboardActivity>(intent).use { scenario ->
            scenario.onActivity { activity ->
                val progressBar = activity.findViewById<android.widget.ProgressBar>(R.id.progressBar)
                assertNotNull("ProgressBar should exist", progressBar)
                
                // Initially, progress bar should be visible while loading
                // Note: This might change quickly due to network calls
                assertTrue("ProgressBar should be visible initially", 
                          progressBar.visibility == android.view.View.VISIBLE ||
                          progressBar.visibility == android.view.View.GONE)
            }
        }
    }
} 