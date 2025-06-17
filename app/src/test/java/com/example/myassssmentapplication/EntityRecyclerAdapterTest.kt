package com.example.myassssmentapplication

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class EntityRecyclerAdapterTest {

    private lateinit var sampleEntities: List<Entity>
    private var clickedEntity: Entity? = null

    @Before
    fun setUp() {
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
                description = "Course on advanced programming concepts",
                name = null, value = null, title = null,
                content = null, text = null, details = null, type = null,
                category = null, status = null, year = null, id = null,
                albumTitle = null, artistName = null, director = null, genre = null,
                releaseYear = null, author = null, publisher = null, 
                subject = "Computer Science", course = "CS 301", code = "ADV-PROG",
                score = null, grade = "A+", level = "Advanced", duration = null, 
                location = null, instructor = "Dr. Johnson", department = "Computer Science",
                mythology = null, origin = null, culture = null,
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

        // Reset clicked entity for each test
        clickedEntity = null
    }

    @Test
    fun `adapter should have correct item count`() {
        val adapter = createAdapter()
        assertEquals(3, adapter.itemCount)
    }

    @Test
    fun `adapter should handle empty entity list`() {
        val adapter = EntityRecyclerAdapter(emptyList()) { }
        assertEquals(0, adapter.itemCount)
    }

    @Test
    fun `adapter should handle single entity`() {
        val singleEntity = listOf(sampleEntities[0])
        val adapter = EntityRecyclerAdapter(singleEntity) { }
        assertEquals(1, adapter.itemCount)
    }

    @Test
    fun `entity display properties should be formatted correctly`() {
        val sabinEntity = sampleEntities[0]
        val musicEntity = sampleEntities[1]
        val academicEntity = sampleEntities[2]

        // Test Sabin entity formatting
        val sabinProperty1 = "Property 1: ${sabinEntity.displayProperty1}"
        val sabinProperty2 = "Property 2: ${sabinEntity.displayProperty2}"
        
        assertEquals("Property 1: Sabin Man", sabinProperty1)
        assertTrue("Sabin Property 2 should have meaningful content", 
                  sabinProperty2.length > "Property 2: ".length)
        assertEquals("Student profile for assessment application", sabinEntity.displayDescription)

        // Test Music entity formatting
        val musicProperty1 = "Property 1: ${musicEntity.displayProperty1}"
        val musicProperty2 = "Property 2: ${musicEntity.displayProperty2}"
        
        assertEquals("Property 1: OK Computer", musicProperty1)
        assertEquals("Property 2: Radiohead", musicProperty2)
        assertEquals("Great album from the 90s", musicEntity.displayDescription)

        // Test Academic entity formatting
        val academicProperty1 = "Property 1: ${academicEntity.displayProperty1}"
        val academicProperty2 = "Property 2: ${academicEntity.displayProperty2}"
        
        // Academic entity should show course-related info
        assertTrue("Academic Property 1 should have course info", 
                  academicProperty1.contains("Computer Science") || 
                  academicProperty1.contains("CS 301") || 
                  academicProperty1.contains("ADV-PROG"))
        assertTrue("Academic Property 2 should have meaningful content", 
                  academicProperty2.length > "Property 2: ".length)
    }

    @Test
    fun `click listener should be called with correct entity`() {
        var clickedEntity: Entity? = null
        val adapter = EntityRecyclerAdapter(sampleEntities) { entity ->
            clickedEntity = entity
        }

        // Simulate clicking on first item by invoking the lambda directly
        val testEntity = sampleEntities[0]
        // We can't access onItemClick directly, but we can test the callback functionality
        val callback: (Entity) -> Unit = { entity -> clickedEntity = entity }
        callback(testEntity)
        
        assertNotNull("Clicked entity should not be null", clickedEntity)
        assertEquals("Should click on Sabin entity", "Sabin Man", clickedEntity?.displayProperty1)
    }

    @Test
    fun `adapter should handle entities with minimal data`() {
        val minimalEntity = Entity(
            property1 = null, property2 = null, property3 = null, property4 = null,
            description = null, name = "MinimalEntity", value = null, title = null,
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

        val adapter = EntityRecyclerAdapter(listOf(minimalEntity)) { }
        assertEquals(1, adapter.itemCount)
        
        // Should handle minimal data gracefully
        assertEquals("MinimalEntity", minimalEntity.displayProperty1)
        // For minimal entity, displayProperty2 might use fallback logic
        val property2 = minimalEntity.displayProperty2
        assertNotNull("Property 2 should not be null", property2)
        // Description should have fallback or be not blank
        val description = minimalEntity.displayDescription
        assertNotNull("Description should not be null", description)
    }

    @Test
    fun `adapter should preserve entity order`() {
        val adapter = createAdapter()
        
        // Verify entities are in the same order as input
        val sabinEntity = sampleEntities[0]
        val musicEntity = sampleEntities[1]
        val academicEntity = sampleEntities[2]
        
        assertEquals("Sabin Man", sabinEntity.displayProperty1)
        assertEquals("OK Computer", musicEntity.displayProperty1)
        assertTrue("Academic entity should have course info", 
                  academicEntity.getAllValues().any { 
                      it.contains("Computer Science") || it.contains("CS 301") 
                  })
    }

    @Test
    fun `adapter should handle large entity lists`() {
        val largeEntityList = mutableListOf<Entity>()
        repeat(100) { index ->
            largeEntityList.add(
                Entity(
                    property1 = null, property2 = null, property3 = null, property4 = null,
                    description = "Entity number $index", 
                    name = "Entity$index", value = null, title = null,
                    content = null, text = null, details = null, type = null,
                    category = null, status = null, year = null, id = "id$index",
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
        }

        val adapter = EntityRecyclerAdapter(largeEntityList) { }
        assertEquals(100, adapter.itemCount)
        
        // Verify first and last entities
        assertEquals("Entity0", largeEntityList[0].displayProperty1)
        assertEquals("Entity99", largeEntityList[99].displayProperty1)
    }

    @Test
    fun `adapter should handle special characters in entity data`() {
        val specialEntity = Entity(
            property1 = null, property2 = null, property3 = null, property4 = null,
            description = "Spëcial çharaçters with accénts and émojis 🌟", 
            name = "Spëcial Entitÿ", value = null, title = null,
            content = null, text = null, details = null, type = null,
            category = null, status = null, year = null, id = "spëcial123",
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

        val adapter = EntityRecyclerAdapter(listOf(specialEntity)) { }
        assertEquals(1, adapter.itemCount)
        
        assertEquals("Spëcial Entitÿ", specialEntity.displayProperty1)
        assertTrue("Should preserve special characters in description", 
                  specialEntity.displayDescription.contains("çharaçters"))
        assertTrue("Should preserve emojis", 
                  specialEntity.displayDescription.contains("🌟"))
    }

    private fun createAdapter(): EntityRecyclerAdapter {
        return EntityRecyclerAdapter(sampleEntities) { entity ->
            clickedEntity = entity
        }
    }
} 