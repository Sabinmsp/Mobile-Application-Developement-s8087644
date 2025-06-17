/**
 * DashboardResponse.kt
 * 
 * PURPOSE: Dashboard API Response Data Models
 * This file defines the data structures received from the dashboard API endpoint.
 * 
 * FUNCTIONALITY:
 * - Represents dashboard data response from the server
 * - Automatically deserialized from JSON by Gson converter
 * - Provides flexible field mapping for different API response formats
 * - Handles null values gracefully with dynamic property detection
 * - Works with any student's API response structure
 * 
 * DATA STRUCTURES:
 * 
 * DashboardResponse:
 * {
 *   "entities": [...],
 *   "entityTotal": 5
 * }
 * 
 * Entity (dynamic field mapping):
 * The Entity class can handle any JSON structure and will automatically
 * map the first available non-null properties to displayProperty1, displayProperty2, etc.
 * 
 * SMART FIELD HANDLING:
 * - Dynamically finds the first 4 non-null, non-empty string properties
 * - Uses the first available property as displayProperty1
 * - Uses the second available property as displayProperty2
 * - Uses description field or any field containing "description" for displayDescription
 * - Ignores ID fields and empty values
 * 
 * API INTEGRATION:
 * - Received from GET /dashboard/{keypass} endpoint
 * - Processed by DashboardActivity
 * - Used by EntityRecyclerAdapter for list display
 * - Passed to DetailsActivity for individual entity view
 */
package com.example.myassssmentapplication

import com.google.gson.annotations.SerializedName

data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)

data class Entity(
    // Primary dynamic fields for any student API response
    @SerializedName("property1") val property1: String?,
    @SerializedName("property2") val property2: String?,
    @SerializedName("property3") val property3: String?,
    @SerializedName("property4") val property4: String?,
    @SerializedName("description") val description: String?,
    
    // Common field names found in student APIs
    @SerializedName("name") val name: String?,
    @SerializedName("value") val value: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("text") val text: String?,
    @SerializedName("details") val details: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("year") val year: Int?,
    @SerializedName("id") val id: String?,
    
    // Additional common fields that might appear in any student API
    @SerializedName("albumTitle") val albumTitle: String?,
    @SerializedName("artistName") val artistName: String?,
    @SerializedName("director") val director: String?,
    @SerializedName("genre") val genre: String?,
    @SerializedName("releaseYear") val releaseYear: Int?,
    @SerializedName("author") val author: String?,
    @SerializedName("publisher") val publisher: String?,
    @SerializedName("subject") val subject: String?,
    @SerializedName("course") val course: String?,
    @SerializedName("code") val code: String?,
    @SerializedName("score") val score: String?,
    @SerializedName("grade") val grade: String?,
    @SerializedName("level") val level: String?,
    @SerializedName("duration") val duration: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("instructor") val instructor: String?,
    @SerializedName("department") val department: String?,
    
    // Extended field detection for more student APIs
    @SerializedName("mythology") val mythology: String?,
    @SerializedName("origin") val origin: String?,
    @SerializedName("culture") val culture: String?,
    @SerializedName("pantheon") val pantheon: String?,
    @SerializedName("domain") val domain: String?,
    @SerializedName("symbol") val symbol: String?,
    @SerializedName("power") val power: String?,
    @SerializedName("attribute") val attribute: String?,
    @SerializedName("role") val role: String?,
    @SerializedName("realm") val realm: String?,
    @SerializedName("family") val family: String?,
    @SerializedName("parent") val parent: String?,
    @SerializedName("child") val child: String?,
    @SerializedName("spouse") val spouse: String?,
    @SerializedName("weapon") val weapon: String?,
    @SerializedName("animal") val animal: String?,
    @SerializedName("element") val element: String?,
    @SerializedName("color") val color: String?,
    @SerializedName("number") val number: String?,
    @SerializedName("day") val day: String?,
    @SerializedName("month") val month: String?,
    @SerializedName("season") val season: String?,
    @SerializedName("planet") val planet: String?,
    @SerializedName("star") val star: String?,
    @SerializedName("stone") val stone: String?,
    @SerializedName("tree") val tree: String?,
    @SerializedName("flower") val flower: String?,
    @SerializedName("festival") val festival: String?,
    @SerializedName("temple") val temple: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("region") val region: String?
) {
    
    /**
     * Gets ALL non-empty values from ANY fields in this entity
     * Simple approach: just collect whatever values exist, don't worry about field names
     */
    fun getAllValues(): List<String> {
        val allValues = mutableListOf<String>()
        
        // Check ALL possible fields and collect their values
        listOfNotNull(
            // Primary fields
            property1?.takeIf { it.isNotBlank() },
            property2?.takeIf { it.isNotBlank() },
            property3?.takeIf { it.isNotBlank() },
            property4?.takeIf { it.isNotBlank() },
            
            // Common fields
            name?.takeIf { it.isNotBlank() },
            value?.takeIf { it.isNotBlank() },
            title?.takeIf { it.isNotBlank() },
            type?.takeIf { it.isNotBlank() },
            category?.takeIf { it.isNotBlank() },
            status?.takeIf { it.isNotBlank() },
            year?.takeIf { it > 0 }?.toString(),
            
            // Music/Media fields
            albumTitle?.takeIf { it.isNotBlank() },
            artistName?.takeIf { it.isNotBlank() },
            director?.takeIf { it.isNotBlank() },
            genre?.takeIf { it.isNotBlank() },
            releaseYear?.takeIf { it > 0 }?.toString(),
            
            // Academic fields
            author?.takeIf { it.isNotBlank() },
            publisher?.takeIf { it.isNotBlank() },
            subject?.takeIf { it.isNotBlank() },
            course?.takeIf { it.isNotBlank() },
            code?.takeIf { it.isNotBlank() },
            score?.takeIf { it.isNotBlank() },
            grade?.takeIf { it.isNotBlank() },
            level?.takeIf { it.isNotBlank() },
            duration?.takeIf { it.isNotBlank() },
            location?.takeIf { it.isNotBlank() },
            instructor?.takeIf { it.isNotBlank() },
            department?.takeIf { it.isNotBlank() },
            
            // Extended fields
            mythology?.takeIf { it.isNotBlank() },
            origin?.takeIf { it.isNotBlank() },
            culture?.takeIf { it.isNotBlank() },
            pantheon?.takeIf { it.isNotBlank() },
            domain?.takeIf { it.isNotBlank() },
            symbol?.takeIf { it.isNotBlank() },
            power?.takeIf { it.isNotBlank() },
            attribute?.takeIf { it.isNotBlank() },
            role?.takeIf { it.isNotBlank() },
            realm?.takeIf { it.isNotBlank() },
            family?.takeIf { it.isNotBlank() },
            parent?.takeIf { it.isNotBlank() },
            child?.takeIf { it.isNotBlank() },
            spouse?.takeIf { it.isNotBlank() },
            weapon?.takeIf { it.isNotBlank() },
            animal?.takeIf { it.isNotBlank() },
            element?.takeIf { it.isNotBlank() },
            color?.takeIf { it.isNotBlank() },
            number?.takeIf { it.isNotBlank() },
            day?.takeIf { it.isNotBlank() },
            month?.takeIf { it.isNotBlank() },
            season?.takeIf { it.isNotBlank() },
            planet?.takeIf { it.isNotBlank() },
            star?.takeIf { it.isNotBlank() },
            stone?.takeIf { it.isNotBlank() },
            tree?.takeIf { it.isNotBlank() },
            flower?.takeIf { it.isNotBlank() },
            festival?.takeIf { it.isNotBlank() },
            temple?.takeIf { it.isNotBlank() },
            city?.takeIf { it.isNotBlank() },
            country?.takeIf { it.isNotBlank() },
            region?.takeIf { it.isNotBlank() }
        ).forEach { value ->
            // Skip IDs and very long description text, but allow shorter content
            if (!value.contains("id", ignoreCase = true) && 
                value != description && 
                value != details &&
                value.length < 150) {  // Increased from 100 to 150 to allow more values
                allValues.add(value)
            }
        }
        
        // If we only found 1 value, try to add fallback values
        if (allValues.size == 1) {
            // Try to use ID as Property 2 (many APIs have meaningful IDs)
            id?.takeIf { it.isNotBlank() && !it.contains("null", ignoreCase = true) }?.let { 
                allValues.add("ID: $it") 
            }
            
            // Try to use first few words of description as Property 2
            if (allValues.size == 1) {
                description?.takeIf { it.isNotBlank() }?.let { desc ->
                    val firstWords = desc.split(" ").take(3).joinToString(" ")
                    if (firstWords.isNotBlank() && firstWords.length < 50) {
                        allValues.add("Type: $firstWords...")
                    }
                }
            }
            
            // Try content field if it's short enough
            if (allValues.size == 1) {
                content?.takeIf { it.isNotBlank() && it.length < 100 }?.let { 
                    allValues.add("Info: $it") 
                }
            }
            
            // Try text field if it's short enough  
            if (allValues.size == 1) {
                text?.takeIf { it.isNotBlank() && it.length < 100 }?.let { 
                    allValues.add("Detail: $it") 
                }
            }
        }
        
        try {
            android.util.Log.d("EntityDebug", "All values found: $allValues")
        } catch (e: RuntimeException) {
            // Log is not available in unit tests, ignore
        }
        return allValues.distinct() // Remove duplicates
    }
    
    /**
     * Gets description from dedicated description fields
     */
    fun getDescriptionText(): String {
        return description?.takeIf { it.isNotBlank() }
            ?: content?.takeIf { it.isNotBlank() }
            ?: details?.takeIf { it.isNotBlank() }
            ?: text?.takeIf { it.isNotBlank() }
            ?: "No description available"
    }
    
    /**
     * Gets ALL actual field names and values that exist in this entity
     * NO GUESSING - just shows whatever is actually there
     * Using manual field checking instead of reflection for better compatibility
     */
    fun getAllActualFields(): List<Pair<String, String>> {
        val regularFields = mutableListOf<Pair<String, String>>()
        val descriptionFields = mutableListOf<Pair<String, String>>()
        
        // Primary fields (usually shorter values, good for Property 1 & 2)
        property1?.takeIf { it.isNotBlank() }?.let { regularFields.add("Property 1" to it) }
        property2?.takeIf { it.isNotBlank() }?.let { regularFields.add("Property 2" to it) }
        property3?.takeIf { it.isNotBlank() }?.let { regularFields.add("Property 3" to it) }
        property4?.takeIf { it.isNotBlank() }?.let { regularFields.add("Property 4" to it) }
        
        // Common short fields (good for Property 1 & 2)
        name?.takeIf { it.isNotBlank() }?.let { regularFields.add("Name" to it) }
        value?.takeIf { it.isNotBlank() }?.let { regularFields.add("Value" to it) }
        title?.takeIf { it.isNotBlank() }?.let { regularFields.add("Title" to it) }
        type?.takeIf { it.isNotBlank() }?.let { regularFields.add("Type" to it) }
        category?.takeIf { it.isNotBlank() }?.let { regularFields.add("Category" to it) }
        status?.takeIf { it.isNotBlank() }?.let { regularFields.add("Status" to it) }
        year?.takeIf { it > 0 }?.let { regularFields.add("Year" to it.toString()) }
        
        // Music/Media fields (good for Property 1 & 2)
        albumTitle?.takeIf { it.isNotBlank() }?.let { regularFields.add("Album Title" to it) }
        artistName?.takeIf { it.isNotBlank() }?.let { regularFields.add("Artist Name" to it) }
        director?.takeIf { it.isNotBlank() }?.let { regularFields.add("Director" to it) }
        genre?.takeIf { it.isNotBlank() }?.let { regularFields.add("Genre" to it) }
        releaseYear?.takeIf { it > 0 }?.let { regularFields.add("Release Year" to it.toString()) }
        
        // Academic fields (good for Property 1 & 2)
        author?.takeIf { it.isNotBlank() }?.let { regularFields.add("Author" to it) }
        publisher?.takeIf { it.isNotBlank() }?.let { regularFields.add("Publisher" to it) }
        subject?.takeIf { it.isNotBlank() }?.let { regularFields.add("Subject" to it) }
        course?.takeIf { it.isNotBlank() }?.let { regularFields.add("Course" to it) }
        code?.takeIf { it.isNotBlank() }?.let { regularFields.add("Code" to it) }
        score?.takeIf { it.isNotBlank() }?.let { regularFields.add("Score" to it) }
        grade?.takeIf { it.isNotBlank() }?.let { regularFields.add("Grade" to it) }
        level?.takeIf { it.isNotBlank() }?.let { regularFields.add("Level" to it) }
        duration?.takeIf { it.isNotBlank() }?.let { regularFields.add("Duration" to it) }
        location?.takeIf { it.isNotBlank() }?.let { regularFields.add("Location" to it) }
        instructor?.takeIf { it.isNotBlank() }?.let { regularFields.add("Instructor" to it) }
        department?.takeIf { it.isNotBlank() }?.let { regularFields.add("Department" to it) }
        
        // Extended mythology/culture fields (likely to exist in student APIs)
        mythology?.takeIf { it.isNotBlank() }?.let { regularFields.add("Mythology" to it) }
        origin?.takeIf { it.isNotBlank() }?.let { regularFields.add("Origin" to it) }
        culture?.takeIf { it.isNotBlank() }?.let { regularFields.add("Culture" to it) }
        pantheon?.takeIf { it.isNotBlank() }?.let { regularFields.add("Pantheon" to it) }
        domain?.takeIf { it.isNotBlank() }?.let { regularFields.add("Domain" to it) }
        symbol?.takeIf { it.isNotBlank() }?.let { regularFields.add("Symbol" to it) }
        power?.takeIf { it.isNotBlank() }?.let { regularFields.add("Power" to it) }
        attribute?.takeIf { it.isNotBlank() }?.let { regularFields.add("Attribute" to it) }
        role?.takeIf { it.isNotBlank() }?.let { regularFields.add("Role" to it) }
        realm?.takeIf { it.isNotBlank() }?.let { regularFields.add("Realm" to it) }
        family?.takeIf { it.isNotBlank() }?.let { regularFields.add("Family" to it) }
        parent?.takeIf { it.isNotBlank() }?.let { regularFields.add("Parent" to it) }
        child?.takeIf { it.isNotBlank() }?.let { regularFields.add("Child" to it) }
        spouse?.takeIf { it.isNotBlank() }?.let { regularFields.add("Spouse" to it) }
        weapon?.takeIf { it.isNotBlank() }?.let { regularFields.add("Weapon" to it) }
        animal?.takeIf { it.isNotBlank() }?.let { regularFields.add("Animal" to it) }
        element?.takeIf { it.isNotBlank() }?.let { regularFields.add("Element" to it) }
        color?.takeIf { it.isNotBlank() }?.let { regularFields.add("Color" to it) }
        number?.takeIf { it.isNotBlank() }?.let { regularFields.add("Number" to it) }
        day?.takeIf { it.isNotBlank() }?.let { regularFields.add("Day" to it) }
        month?.takeIf { it.isNotBlank() }?.let { regularFields.add("Month" to it) }
        season?.takeIf { it.isNotBlank() }?.let { regularFields.add("Season" to it) }
        planet?.takeIf { it.isNotBlank() }?.let { regularFields.add("Planet" to it) }
        star?.takeIf { it.isNotBlank() }?.let { regularFields.add("Star" to it) }
        stone?.takeIf { it.isNotBlank() }?.let { regularFields.add("Stone" to it) }
        tree?.takeIf { it.isNotBlank() }?.let { regularFields.add("Tree" to it) }
        flower?.takeIf { it.isNotBlank() }?.let { regularFields.add("Flower" to it) }
        festival?.takeIf { it.isNotBlank() }?.let { regularFields.add("Festival" to it) }
        temple?.takeIf { it.isNotBlank() }?.let { regularFields.add("Temple" to it) }
        city?.takeIf { it.isNotBlank() }?.let { regularFields.add("City" to it) }
        country?.takeIf { it.isNotBlank() }?.let { regularFields.add("Country" to it) }
        region?.takeIf { it.isNotBlank() }?.let { regularFields.add("Region" to it) }
        
        // Description fields (longer text, separate from Property 1 & 2)
        description?.takeIf { it.isNotBlank() }?.let { descriptionFields.add("Description" to it) }
        content?.takeIf { it.isNotBlank() }?.let { descriptionFields.add("Content" to it) }
        text?.takeIf { it.isNotBlank() }?.let { descriptionFields.add("Text" to it) }
        details?.takeIf { it.isNotBlank() }?.let { descriptionFields.add("Details" to it) }
        
        // Combine all fields (regular fields first, then descriptions)
        val allFields = regularFields + descriptionFields
        
        try {
            android.util.Log.d("EntityDebug", "Found ${regularFields.size} regular fields: $regularFields")
            android.util.Log.d("EntityDebug", "Found ${descriptionFields.size} description fields: $descriptionFields")
            android.util.Log.d("EntityDebug", "Total ${allFields.size} fields: $allFields")
        } catch (e: RuntimeException) {
            // Log is not available in unit tests, ignore
        }
        
        return allFields
    }
    
    /**
     * Gets only regular fields (non-description fields) for Property 1 and Property 2
     */
    fun getRegularFields(): List<Pair<String, String>> {
        val regularFields = mutableListOf<Pair<String, String>>()
        
        // Primary fields
        property1?.takeIf { it.isNotBlank() }?.let { regularFields.add("Property 1" to it) }
        property2?.takeIf { it.isNotBlank() }?.let { regularFields.add("Property 2" to it) }
        property3?.takeIf { it.isNotBlank() }?.let { regularFields.add("Property 3" to it) }
        property4?.takeIf { it.isNotBlank() }?.let { regularFields.add("Property 4" to it) }
        
        // Common short fields
        name?.takeIf { it.isNotBlank() }?.let { regularFields.add("Name" to it) }
        value?.takeIf { it.isNotBlank() }?.let { regularFields.add("Value" to it) }
        title?.takeIf { it.isNotBlank() }?.let { regularFields.add("Title" to it) }
        type?.takeIf { it.isNotBlank() }?.let { regularFields.add("Type" to it) }
        category?.takeIf { it.isNotBlank() }?.let { regularFields.add("Category" to it) }
        status?.takeIf { it.isNotBlank() }?.let { regularFields.add("Status" to it) }
        year?.takeIf { it > 0 }?.let { regularFields.add("Year" to it.toString()) }
        
        // Music/Media fields
        albumTitle?.takeIf { it.isNotBlank() }?.let { regularFields.add("Album Title" to it) }
        artistName?.takeIf { it.isNotBlank() }?.let { regularFields.add("Artist Name" to it) }
        director?.takeIf { it.isNotBlank() }?.let { regularFields.add("Director" to it) }
        genre?.takeIf { it.isNotBlank() }?.let { regularFields.add("Genre" to it) }
        releaseYear?.takeIf { it > 0 }?.let { regularFields.add("Release Year" to it.toString()) }
        
        // Academic fields
        author?.takeIf { it.isNotBlank() }?.let { regularFields.add("Author" to it) }
        publisher?.takeIf { it.isNotBlank() }?.let { regularFields.add("Publisher" to it) }
        subject?.takeIf { it.isNotBlank() }?.let { regularFields.add("Subject" to it) }
        course?.takeIf { it.isNotBlank() }?.let { regularFields.add("Course" to it) }
        code?.takeIf { it.isNotBlank() }?.let { regularFields.add("Code" to it) }
        score?.takeIf { it.isNotBlank() }?.let { regularFields.add("Score" to it) }
        grade?.takeIf { it.isNotBlank() }?.let { regularFields.add("Grade" to it) }
        level?.takeIf { it.isNotBlank() }?.let { regularFields.add("Level" to it) }
        duration?.takeIf { it.isNotBlank() }?.let { regularFields.add("Duration" to it) }
        location?.takeIf { it.isNotBlank() }?.let { regularFields.add("Location" to it) }
        instructor?.takeIf { it.isNotBlank() }?.let { regularFields.add("Instructor" to it) }
        department?.takeIf { it.isNotBlank() }?.let { regularFields.add("Department" to it) }
        
        // Extended mythology/culture fields (likely to exist in student APIs)
        mythology?.takeIf { it.isNotBlank() }?.let { regularFields.add("Mythology" to it) }
        origin?.takeIf { it.isNotBlank() }?.let { regularFields.add("Origin" to it) }
        culture?.takeIf { it.isNotBlank() }?.let { regularFields.add("Culture" to it) }
        pantheon?.takeIf { it.isNotBlank() }?.let { regularFields.add("Pantheon" to it) }
        domain?.takeIf { it.isNotBlank() }?.let { regularFields.add("Domain" to it) }
        symbol?.takeIf { it.isNotBlank() }?.let { regularFields.add("Symbol" to it) }
        power?.takeIf { it.isNotBlank() }?.let { regularFields.add("Power" to it) }
        attribute?.takeIf { it.isNotBlank() }?.let { regularFields.add("Attribute" to it) }
        role?.takeIf { it.isNotBlank() }?.let { regularFields.add("Role" to it) }
        realm?.takeIf { it.isNotBlank() }?.let { regularFields.add("Realm" to it) }
        family?.takeIf { it.isNotBlank() }?.let { regularFields.add("Family" to it) }
        parent?.takeIf { it.isNotBlank() }?.let { regularFields.add("Parent" to it) }
        child?.takeIf { it.isNotBlank() }?.let { regularFields.add("Child" to it) }
        spouse?.takeIf { it.isNotBlank() }?.let { regularFields.add("Spouse" to it) }
        weapon?.takeIf { it.isNotBlank() }?.let { regularFields.add("Weapon" to it) }
        animal?.takeIf { it.isNotBlank() }?.let { regularFields.add("Animal" to it) }
        element?.takeIf { it.isNotBlank() }?.let { regularFields.add("Element" to it) }
        color?.takeIf { it.isNotBlank() }?.let { regularFields.add("Color" to it) }
        number?.takeIf { it.isNotBlank() }?.let { regularFields.add("Number" to it) }
        day?.takeIf { it.isNotBlank() }?.let { regularFields.add("Day" to it) }
        month?.takeIf { it.isNotBlank() }?.let { regularFields.add("Month" to it) }
        season?.takeIf { it.isNotBlank() }?.let { regularFields.add("Season" to it) }
        planet?.takeIf { it.isNotBlank() }?.let { regularFields.add("Planet" to it) }
        star?.takeIf { it.isNotBlank() }?.let { regularFields.add("Star" to it) }
        stone?.takeIf { it.isNotBlank() }?.let { regularFields.add("Stone" to it) }
        tree?.takeIf { it.isNotBlank() }?.let { regularFields.add("Tree" to it) }
        flower?.takeIf { it.isNotBlank() }?.let { regularFields.add("Flower" to it) }
        festival?.takeIf { it.isNotBlank() }?.let { regularFields.add("Festival" to it) }
        temple?.takeIf { it.isNotBlank() }?.let { regularFields.add("Temple" to it) }
        city?.takeIf { it.isNotBlank() }?.let { regularFields.add("City" to it) }
        country?.takeIf { it.isNotBlank() }?.let { regularFields.add("Country" to it) }
        region?.takeIf { it.isNotBlank() }?.let { regularFields.add("Region" to it) }
        
        return regularFields
    }
    
    /**
     * Convert camelCase field names to readable format
     * e.g., "albumTitle" -> "Album Title"
     */
    private fun formatFieldName(fieldName: String): String {
        return fieldName
            .replace(Regex("([a-z])([A-Z])"), "$1 $2")
            .split(" ")
            .joinToString(" ") { word ->
                word.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
            }
    }
    
    // Simple direct access to actual field values - NO GUESSING
    val actualField1: Pair<String, String>?
        get() = getRegularFields().getOrNull(0) // First regular field (not description)
    
    val actualField2: Pair<String, String>?
        get() = getRegularFields().getOrNull(1) // Second regular field (not description)
    
    val actualDescription: Pair<String, String>?
        get() {
            // Look for description-like fields first
            description?.takeIf { it.isNotBlank() }?.let { return "Description" to it }
            content?.takeIf { it.isNotBlank() }?.let { return "Content" to it }
            details?.takeIf { it.isNotBlank() }?.let { return "Details" to it }
            text?.takeIf { it.isNotBlank() }?.let { return "Text" to it }
            
            // If no description fields, get the longest regular field
            return getRegularFields().maxByOrNull { it.second.length }
        }
    
    // Simple approach: just use whatever first 2 values exist
    val displayProperty1: String 
        get() {
            val allValues = getAllValues()
            return allValues.getOrNull(0) ?: "No data available"
        }
    
    val displayProperty2: String 
        get() {
            val allValues = getAllValues()
            return allValues.getOrNull(1) ?: "No second value available"
        }
    
    val displayPropertyName1: String = "Property 1"
    val displayPropertyName2: String = "Property 2"
    
    val displayDescription: String 
        get() = getDescriptionText()
} 