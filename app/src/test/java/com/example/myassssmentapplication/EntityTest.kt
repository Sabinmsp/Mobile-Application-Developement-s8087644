package com.example.myassssmentapplication

import org.junit.Test
import org.junit.Assert.*

class EntityTest {

    @Test
    fun `getAllValues should return empty list when all fields are null or empty`() {
        val entity = Entity(
            property1 = null, property2 = null, property3 = null, property4 = null,
            description = null, name = null, value = null, title = null,
            content = null, text = null, details = null, type = null,
            category = null, status = null, year = null, id = null,
            // All other fields null...
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
        
        val values = entity.getAllValues()
        assertTrue("Should return empty list when no fields have values", values.isEmpty())
    }

    @Test
    fun `getAllValues should return single value for music entity`() {
        val entity = Entity(
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
        )
        
        val values = entity.getAllValues()
        assertTrue("Should find multiple values", values.size >= 3)
        assertTrue("Should contain album title", values.contains("OK Computer"))
        assertTrue("Should contain artist name", values.contains("Radiohead"))
        assertTrue("Should contain genre", values.contains("Alternative Rock"))
        assertTrue("Should contain release year", values.contains("1997"))
    }

    @Test
    fun `displayProperty1 should return first available value`() {
        val entity = Entity(
            property1 = null, property2 = null, property3 = null, property4 = null,
            description = "Description text", 
            name = "Zeus", value = null, title = null,
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
        
        assertEquals("Zeus", entity.displayProperty1)
    }

    @Test
    fun `displayProperty2 should use fallback when only one main value exists`() {
        val entity = Entity(
            property1 = null, property2 = null, property3 = null, property4 = null,
            description = "King of the Olympian gods", 
            name = "Zeus", value = null, title = null,
            content = null, text = null, details = null, type = null,
            category = null, status = null, year = null, id = "zeus001",
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
        
        val property2 = entity.displayProperty2
        // Should use fallback strategy - either ID or description preview
        assertTrue("Property 2 should not be 'No second value available'", 
                  property2 != "No second value available")
    }

    @Test
    fun `getDescriptionText should return description field`() {
        val entity = Entity(
            property1 = null, property2 = null, property3 = null, property4 = null,
            description = "This is the description", 
            name = null, value = null, title = null,
            content = "This is content", text = null, details = null, type = null,
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
        
        assertEquals("This is the description", entity.getDescriptionText())
    }

    @Test
    fun `getDescriptionText should fallback to content when description is null`() {
        val entity = Entity(
            property1 = null, property2 = null, property3 = null, property4 = null,
            description = null, 
            name = null, value = null, title = null,
            content = "This is content", text = null, details = null, type = null,
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
        
        assertEquals("This is content", entity.getDescriptionText())
    }
} 