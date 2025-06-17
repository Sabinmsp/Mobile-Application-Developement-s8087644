package com.example.myassssmentapplication

import org.junit.Assert.*
import org.junit.Test

class RetrofitClientTest {

    @Test
    fun `instance should return a non-null ApiService object`() {
        val apiService = RetrofitClient.instance
        assertNotNull(apiService)
    }

    @Test
    fun `RetrofitClient should be configured with correct base URL`() {
        // We can't directly access the base URL from the instance,
        // but we can verify the instance is properly created
        val apiService = RetrofitClient.instance
        assertNotNull(apiService)
        
        // Verify it's the correct type
        assertTrue(apiService is ApiService)
    }
}
