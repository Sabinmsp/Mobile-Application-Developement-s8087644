/**
 * EntityAdapter.kt
 * 
 * PURPOSE: Custom ListView Adapter for Entity Display
 * This file manages how entity data is displayed in the dashboard ListView.
 * 
 * FUNCTIONALITY:
 * - Extends BaseAdapter to provide custom list item layouts
 * - Converts Entity objects into displayable list items
 * - Handles view recycling for optimal performance
 * - Applies custom styling and formatting to list items
 * - Manages data binding between entities and UI elements
 * 
 * DISPLAY FORMAT:
 * - Primary text: "Property1 - Property2" (bold, larger font)
 * - Secondary text: Description (smaller font, secondary color)
 * - Uses Android's built-in simple_list_item_2 layout
 * - Applies custom text sizing and colors
 * 
 * DATA HANDLING:
 * - Uses Entity.displayProperty1, displayProperty2, displayDescription
 * - Automatically handles null values through Entity's smart properties
 * - Shows meaningful text even when API data is missing
 * 
 * PERFORMANCE:
 * - Implements view recycling (convertView pattern)
 * - Efficient for large lists of entities
 * - Minimizes memory usage and scroll lag
 * 
 * USAGE:
 * - Created by DashboardActivity
 * - Attached to ListView for entity display
 * - Updates automatically when entity list changes
 */
package com.example.myassssmentapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class EntityAdapter(
    private val context: Context,
    private val entities: List<Entity>
) : BaseAdapter() {

    override fun getCount(): Int = entities.size

    override fun getItem(position: Int): Entity = entities[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(
            android.R.layout.simple_list_item_2, parent, false
        )

        val entity = entities[position]
        
        val text1 = view.findViewById<TextView>(android.R.id.text1)
        val text2 = view.findViewById<TextView>(android.R.id.text2)
        
        text1.text = "${entity.displayProperty1} - ${entity.displayProperty2}"
        text2.text = entity.displayDescription
        
        // Style the text
        text1.textSize = 16f
        text2.textSize = 14f
        text2.setTextColor(context.getColor(R.color.secondary_text))
        
        return view
    }
} 