package com.example.myassssmentapplication



import org.junit.Test
import org.junit.Assert.*

class LoginRequestTest {

    @Test
    fun `login request fields should be set correctly`() {
        val request = LoginRequest("Sabin Man", "s8087644")
        assertEquals("Sabin Man", request.username)
        assertEquals("s8087644", request.password)
    }
    
    @Test
    fun `login request should handle different username formats`() {
        val request1 = LoginRequest("Sabin Man", "s8087644")
        assertEquals("Sabin Man", request1.username)
        assertEquals("s8087644", request1.password)
        
        val request2 = LoginRequest("sabin.man", "s8087644")
        assertEquals("sabin.man", request2.username)
        assertEquals("s8087644", request2.password)
    }
    
    @Test
    fun `login request should handle empty strings`() {
        val request = LoginRequest("", "")
        assertEquals("", request.username)
        assertEquals("", request.password)
    }
}
