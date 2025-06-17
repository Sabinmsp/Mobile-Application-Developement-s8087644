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
 * Instrumented test for DetailsActivity, which will execute on an Android device.
 * Tests the Android-specific UI components and behavior.
 */
@RunWith(AndroidJUnit4::class)
class DetailsActivityInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.myassssmentapplication", appContext.packageName)
    }

    @Test
    fun detailsActivity_launchWithValidData_shouldNotCrash() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), DetailsActivity::class.java).apply {
            putExtra("property1", "Sabin Man")
            putExtra("property2", "s8087644")
            putExtra("description", "Student profile for assessment application")
        }

        ActivityScenario.launch<DetailsActivity>(intent).use { scenario ->
            scenario.onActivity { activity ->
                assertNotNull("Activity should not be null", activity)
                assertFalse("Activity should not be finishing", activity.isFinishing)
            }
        }
    }

    @Test
    fun detailsActivity_hasRequiredUIComponents() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), DetailsActivity::class.java).apply {
            putExtra("property1", "Test Property 1")
            putExtra("property2", "Test Property 2")
            putExtra("description", "Test Description")
        }

        ActivityScenario.launch<DetailsActivity>(intent).use { scenario ->
            scenario.onActivity { activity ->
                // Verify all required UI components exist
                assertNotNull("Property1 TextView should exist", 
                             activity.findViewById<android.widget.TextView>(R.id.tvProperty1))
                assertNotNull("Property2 TextView should exist", 
                             activity.findViewById<android.widget.TextView>(R.id.tvProperty2))
                assertNotNull("Description TextView should exist", 
                             activity.findViewById<android.widget.TextView>(R.id.tvDescription))
                assertNotNull("Back Button should exist", 
                             activity.findViewById<android.widget.Button>(R.id.btnBack))
                assertNotNull("Logout Button should exist", 
                             activity.findViewById<android.widget.Button>(R.id.btnLogout))
            }
        }
    }

    @Test
    fun detailsActivity_displaysPropertyData_correctly() {
        val testProperty1 = "Sabin Man"
        val testProperty2 = "s8087644"
        val testDescription = "Student profile for assessment application"

        val intent = Intent(ApplicationProvider.getApplicationContext(), DetailsActivity::class.java).apply {
            putExtra("property1", testProperty1)
            putExtra("property2", testProperty2)
            putExtra("description", testDescription)
        }

        ActivityScenario.launch<DetailsActivity>(intent).use { scenario ->
            scenario.onActivity { activity ->
                val tvProperty1 = activity.findViewById<android.widget.TextView>(R.id.tvProperty1)
                val tvProperty2 = activity.findViewById<android.widget.TextView>(R.id.tvProperty2)
                val tvDescription = activity.findViewById<android.widget.TextView>(R.id.tvDescription)

                // Check if text is set correctly
                assertEquals("Property 1: $testProperty1", tvProperty1.text.toString())
                assertEquals("Property 2: $testProperty2", tvProperty2.text.toString())
                assertEquals(testDescription, tvDescription.text.toString())
            }
        }
    }
} 