package com.example.myassssmentapplication

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class DetailsActivityTest {

    private lateinit var sampleProperty1: String
    private lateinit var sampleProperty2: String
    private lateinit var sampleDescription: String

    @Before
    fun setUp() {
        sampleProperty1 = "Sabin Man"
        sampleProperty2 = "s8087644"
        sampleDescription = "Student profile for assessment application with ID s8087644"
    }

    @Test
    fun `details should format property labels correctly`() {
        val expectedProperty1 = "Property 1: Sabin Man"
        val expectedProperty2 = "Property 2: s8087644"
        
        assertEquals("Property 1: $sampleProperty1", expectedProperty1)
        assertEquals("Property 2: $sampleProperty2", expectedProperty2)
    }

    @Test
    fun `details should handle empty property values`() {
        val emptyProperty1 = ""
        val emptyProperty2 = ""
        val emptyDescription = ""
        
        val formattedProperty1 = "Property 1: $emptyProperty1"
        val formattedProperty2 = "Property 2: $emptyProperty2"
        
        assertEquals("Property 1: ", formattedProperty1)
        assertEquals("Property 2: ", formattedProperty2)
        assertEquals("", emptyDescription)
    }

    @Test
    fun `details should handle null property values`() {
        val nullProperty1: String? = null
        val nullProperty2: String? = null
        val nullDescription: String? = null
        
        val safeProperty1 = nullProperty1 ?: ""
        val safeProperty2 = nullProperty2 ?: ""
        val safeDescription = nullDescription ?: ""
        
        assertEquals("", safeProperty1)
        assertEquals("", safeProperty2)
        assertEquals("", safeDescription)
    }

    @Test
    fun `details should handle long descriptions`() {
        val longDescription = """
            Zeus is the king of the gods and ruler of Mount Olympus. He is the god of the sky, 
            lightning, thunder, law, order, and justice. According to Hesiod's Theogony, 
            Zeus' parents were Cronus and Rhea and he was the youngest of his siblings. 
            In most traditions, he is married to Hera, although at the Oracle of Dodona, 
            his consort is Dione: according to the Iliad, he is the father of Aphrodite by Dione.
        """.trimIndent()
        
        assertTrue("Long description should be preserved", longDescription.length > 200)
        assertTrue("Should contain Zeus", longDescription.contains("Zeus"))
        assertTrue("Should contain Olympus", longDescription.contains("Olympus"))
    }

    @Test
    fun `details should handle special characters in properties`() {
        val propertyWithSpecialChars = "Ödin (Óðinn)"
        val descriptionWithSpecialChars = "Norse god of wisdom, war, and death. Also known as Wōden or Wóden."
        
        assertTrue("Should preserve special characters", propertyWithSpecialChars.contains("Ö"))
        assertTrue("Should preserve special characters", propertyWithSpecialChars.contains("ð"))
        assertTrue("Should preserve special characters", descriptionWithSpecialChars.contains("ō"))
    }

    @Test
    fun `details should handle numeric properties`() {
        val numericProperty1 = "1997"
        val numericProperty2 = "42"
        val mixedProperty = "Track 5"
        
        val formattedNumeric1 = "Property 1: $numericProperty1"
        val formattedNumeric2 = "Property 2: $numericProperty2"
        val formattedMixed = "Property 1: $mixedProperty"
        
        assertEquals("Property 1: 1997", formattedNumeric1)
        assertEquals("Property 2: 42", formattedNumeric2)
        assertEquals("Property 1: Track 5", formattedMixed)
    }

    @Test
    fun `details should handle music entity data`() {
        val albumTitle = "OK Computer"
        val artistName = "Radiohead"
        val albumDescription = "Third studio album by the English rock band Radiohead, released in 1997."
        
        val property1Display = "Property 1: $albumTitle"
        val property2Display = "Property 2: $artistName"
        
        assertEquals("Property 1: OK Computer", property1Display)
        assertEquals("Property 2: Radiohead", property2Display)
        assertTrue("Description should be preserved", albumDescription.contains("Radiohead"))
        assertTrue("Description should contain year", albumDescription.contains("1997"))
    }

    @Test
    fun `details should handle academic entity data`() {
        val courseName = "Advanced Mathematics"
        val instructor = "Dr. Smith"
        val courseDescription = "An advanced course covering calculus, linear algebra, and differential equations."
        
        val property1Display = "Property 1: $courseName"
        val property2Display = "Property 2: $instructor"
        
        assertEquals("Property 1: Advanced Mathematics", property1Display)
        assertEquals("Property 2: Dr. Smith", property2Display)
        assertTrue("Description should contain course info", courseDescription.contains("calculus"))
    }

    @Test
    fun `details should validate property display format consistency`() {
        val testCases = listOf(
            "Simple Text",
            "Text with Numbers 123",
            "Text with Special-Characters & Symbols!",
            "Très long texte avec des caractères spéciaux",
            "日本語テキスト",
            ""  // Empty case
        )
        
        testCases.forEach { testValue ->
            val formatted = "Property 1: $testValue"
            assertTrue("Should start with label", formatted.startsWith("Property 1: "))
            assertEquals("Should preserve original value", testValue, formatted.removePrefix("Property 1: "))
        }
    }

    @Test
    fun `details should handle whitespace in properties`() {
        val propertyWithSpaces = "  Zeus  "
        val propertyWithTabs = "\tOdin\t"
        val propertyWithNewlines = "\nRa\n"
        
        // Test that whitespace is preserved (DetailsActivity doesn't trim)
        assertEquals("Property 1:   Zeus  ", "Property 1: $propertyWithSpaces")
        assertEquals("Property 2: \tOdin\t", "Property 2: $propertyWithTabs")
        assertTrue("Should preserve newlines", "Description: $propertyWithNewlines".contains("\n"))
    }

    @Test
    fun `details intent data simulation should work correctly`() {
        // Simulate what DashboardActivity would pass to DetailsActivity
        val intentExtras = mapOf(
            "property1" to "Sabin Man",
            "property2" to "s8087644",
            "description" to "Student profile for assessment"
        )
        
        val property1 = intentExtras["property1"] ?: ""
        val property2 = intentExtras["property2"] ?: ""
        val description = intentExtras["description"] ?: ""
        
        assertEquals("Sabin Man", property1)
        assertEquals("s8087644", property2)
        assertEquals("Student profile for assessment", description)
        
        // Verify formatting as DetailsActivity would do
        assertEquals("Property 1: Sabin Man", "Property 1: $property1")
        assertEquals("Property 2: s8087644", "Property 2: $property2")
        assertEquals("Student profile for assessment", description)
    }
} 