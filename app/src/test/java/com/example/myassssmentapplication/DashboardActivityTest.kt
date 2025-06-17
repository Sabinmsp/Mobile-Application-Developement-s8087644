package com.example.myassssmentapplication

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class DashboardActivityTest {

    private lateinit var mockDashboardResponse: DashboardResponse
    private lateinit var sampleEntities: List<Entity>

    @Before
    fun setUp() {
        // Create sample entities for testing
        sampleEntities = listOf(
            Entity(
                property1 = null, property2 = null, property3 = null, property4 = null,
                description = "Student profile for assessment application", 
                name = "Sabin Man", value = null, title = null,
                content = null, text = null, details = null, type = null,
                category = null, status = null, year = null, id = "s8087644",
                albumTitle = null, artistName = null, director = null, genre = null,
                releaseYear = null, author = null, publisher = null, subject = null,
                course = null, code = null, score = null, grade = null,
                level = null, duration = null, location = null, instructor = null,
                department = null,                 mythology = "Student", origin = null, culture = null,
                pantheon = "University", domain = "Assessment", symbol = "s8087644", power = null,
                attribute = null, role = null, realm = null, family = null,
                parent = null, child = null, spouse = null, weapon = null,
                animal = null, element = null, color = null, number = null,
                day = null, month = null, season = null, planet = null,
                star = null, stone = null, tree = null, flower = null,
                festival = null, temple = null, city = null, country = null,
                region = null
            ),
            Entity(
                property1 = null, property2 = null, property3 = null, property4 = null,
                description = "Great album from the 90s", 
                name = null, value = null, title = null,
                content = null, text = null, details = null, type = null,
                category = null, status = null, year = null, id = null,
                albumTitle = "OK Computer", artistName = "Radiohead", 
                director = null, genre = "Alternative Rock", releaseYear = 1997,
                author = null, publisher = null, subject = null,
                course = null, code = null, score = null, grade = null,
                level = null, duration = null, location = null, instructor = null,
                department = null, mythology = null, origin = null, culture = null,
                pantheon = null, domain = null, symbol = null, power = null,
                attribute = null, role = null, realm = null, family = null,
                parent = null, child = null, spouse = null, weapon = null,
                animal = null, element = null, color = null, number = null,
                day = null, month = null, season = null, planet = null,
                star = null, stone = null, tree = null, flower = null,
                festival = null, temple = null, city = null, country = null,
                region = null
            ),
            Entity(
                property1 = null, property2 = null, property3 = null, property4 = null,
                description = null,  // No description field
                name = "Apollo", value = null, title = null,
                content = null, text = null, details = null, type = null,
                category = null, status = null, year = null, id = "apollo001",
                albumTitle = null, artistName = null, director = null, genre = null,
                releaseYear = null, author = null, publisher = null, subject = null,
                course = null, code = null, score = null, grade = null,
                level = null, duration = null, location = null, instructor = null,
                department = null, mythology = null, origin = null, culture = null,
                pantheon = null, domain = null, symbol = null, power = null,
                attribute = null, role = null, realm = null, family = null,
                parent = null, child = null, spouse = null, weapon = null,
                animal = null, element = null, color = null, number = null,
                day = null, month = null, season = null, planet = null,
                star = null, stone = null, tree = null, flower = null,
                festival = null, temple = null, city = null, country = null,
                region = null
            )
        )

        mockDashboardResponse = DashboardResponse(
            entities = sampleEntities,
            entityTotal = sampleEntities.size
        )
    }

    @Test
    fun `dashboard response should have correct entity count`() {
        assertEquals(3, mockDashboardResponse.entityTotal)
        assertEquals(3, mockDashboardResponse.entities.size)
    }

    @Test
    fun `entities should have valid display properties`() {
        val sabinEntity = sampleEntities[0]
        
        // Sabin Man should show name as Property 1
        assertEquals("Sabin Man", sabinEntity.displayProperty1)
        
        // Property 2 should use fallback strategy (ID, pantheon, mythology, etc.)
        val property2 = sabinEntity.displayProperty2
        assertNotEquals("No second value available", property2)
        assertTrue("Property 2 should have meaningful content", 
                  property2.isNotBlank())
        
        // Description should be available
        assertEquals("Student profile for assessment application", sabinEntity.displayDescription)
    }

    @Test
    fun `music entity should display correctly`() {
        val musicEntity = sampleEntities[1]
        
        // Should have album title as Property 1
        assertEquals("OK Computer", musicEntity.displayProperty1)
        
        // Should have artist name as Property 2
        assertEquals("Radiohead", musicEntity.displayProperty2)
        
        // Should have description
        assertEquals("Great album from the 90s", musicEntity.displayDescription)
    }

    @Test
    fun `entity with minimal data should use fallbacks`() {
        val apolloEntity = sampleEntities[2]
        
        // Should have name as Property 1
        assertEquals("Apollo", apolloEntity.displayProperty1)
        
        // Property 2 should use fallback (probably ID)
        val property2 = apolloEntity.displayProperty2
        assertTrue("Property 2 should have fallback value", property2.isNotBlank())
        
        // Description should use fallback
        val description = apolloEntity.displayDescription
        assertTrue("Description should have fallback", description.isNotBlank())
    }

    @Test
    fun `getAllActualFields should return non-empty fields only`() {
        val sabinEntity = sampleEntities[0]
        val actualFields = sabinEntity.getAllActualFields()
        
        // Should contain fields that are not null/empty
        assertTrue("Should have actual fields", actualFields.isNotEmpty())
        
        // All returned fields should have non-empty values
        actualFields.forEach { (fieldName, value) ->
            assertTrue("Field $fieldName should have non-empty value", value.isNotBlank())
        }
        
        // Should contain expected fields from the Sabin entity
        val fieldNames = actualFields.map { it.first }
        assertTrue("Should contain Name", fieldNames.any { it.contains("Name", ignoreCase = true) })
        assertTrue("Should contain Mythology", fieldNames.any { it.contains("Mythology", ignoreCase = true) })
        // Note: ID field might not be returned by getAllActualFields since it only checks specific field names
    }

    @Test
    fun `dashboard should handle empty entity list`() {
        val emptyResponse = DashboardResponse(
            entities = emptyList(),
            entityTotal = 0
        )
        
        assertEquals(0, emptyResponse.entityTotal)
        assertTrue("Entity list should be empty", emptyResponse.entities.isEmpty())
    }

    @Test
    fun `dashboard should handle null fields gracefully`() {
        val entityWithNulls = Entity(
            property1 = null, property2 = null, property3 = null, property4 = null,
            description = null, name = null, value = null, title = null,
            content = null, text = null, details = null, type = null,
            category = null, status = null, year = null, id = null,
            albumTitle = null, artistName = null, director = null, genre = null,
            releaseYear = null, author = null, publisher = null, subject = null,
            course = null, code = null, score = null, grade = null,
            level = null, duration = null, location = null, instructor = null,
            department = null, mythology = null, origin = null, culture = null,
            pantheon = null, domain = null, symbol = null, power = null,
            attribute = null, role = null, realm = null, family = null,
            parent = null, child = null, spouse = null, weapon = null,
            animal = null, element = null, color = null, number = null,
            day = null, month = null, season = null, planet = null,
            star = null, stone = null, tree = null, flower = null,
            festival = null, temple = null, city = null, country = null,
            region = null
        )
        
        // Should not crash and should provide fallback values
        val property1 = entityWithNulls.displayProperty1
        val property2 = entityWithNulls.displayProperty2
        val description = entityWithNulls.displayDescription
        
        assertNotNull("Property 1 should not be null", property1)
        assertNotNull("Property 2 should not be null", property2)
        assertNotNull("Description should not be null", description)
    }

    @Test
    fun `entity with single value should use smart fallbacks`() {
        val singleValueEntity = Entity(
            property1 = null, property2 = null, property3 = null, property4 = null,
            description = "Single entity with only name and description",
            name = "Artemis", value = null, title = null,
            content = null, text = null, details = null, type = null,
            category = null, status = null, year = null, id = null,
            albumTitle = null, artistName = null, director = null, genre = null,
            releaseYear = null, author = null, publisher = null, subject = null,
            course = null, code = null, score = null, grade = null,
            level = null, duration = null, location = null, instructor = null,
            department = null, mythology = null, origin = null, culture = null,
            pantheon = null, domain = null, symbol = null, power = null,
            attribute = null, role = null, realm = null, family = null,
            parent = null, child = null, spouse = null, weapon = null,
            animal = null, element = null, color = null, number = null,
            day = null, month = null, season = null, planet = null,
            star = null, stone = null, tree = null, flower = null,
            festival = null, temple = null, city = null, country = null,
            region = null
        )
        
        assertEquals("Artemis", singleValueEntity.displayProperty1)
        
        // Property 2 should use smart fallback (description preview or type)
        val property2 = singleValueEntity.displayProperty2
        assertNotEquals("No second value available", property2)
        assertTrue("Property 2 should use smart fallback", property2.isNotBlank())
    }

    @Test
    fun `getAllValues should return comprehensive field list`() {
        val sabinEntity = sampleEntities[0]
        val allValues = sabinEntity.getAllValues()
        
        assertTrue("Should have multiple values", allValues.size >= 5)
        assertTrue("Should contain Sabin Man", allValues.contains("Sabin Man"))
        assertTrue("Should contain Student", allValues.contains("Student"))
        assertTrue("Should contain University", allValues.contains("University"))
        assertTrue("Should contain Assessment", allValues.contains("Assessment"))
        assertTrue("Should contain s8087644", allValues.contains("s8087644"))
    }
} 