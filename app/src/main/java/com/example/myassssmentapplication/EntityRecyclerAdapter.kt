/**
 * EntityRecyclerAdapter.kt
 * 
 * PURPOSE: RecyclerView Adapter for Entity Display
 * This file manages how entity data is displayed in the dashboard RecyclerView.
 * 
 * FUNCTIONALITY:
 * - Extends RecyclerView.Adapter with ViewHolder pattern
 * - Converts Entity objects into displayable list items
 * - Handles view recycling for optimal performance
 * - Applies custom styling and formatting to list items
 * - Manages data binding between entities and UI elements
 * - Implements click functionality for navigation to Details screen
 * 
 * DISPLAY FORMAT:
 * - Primary text: "Property1 - Property2" (bold, larger font)
 * - Secondary text: Description (smaller font, secondary color)
 * - Uses custom item layout for better control
 * - CardView for modern material design appearance
 * 
 * DATA HANDLING:
 * - Uses Entity.displayProperty1, displayProperty2, displayDescription
 * - Automatically handles null values through Entity's smart properties
 * - Shows meaningful text even when API data is missing
 * 
 * PERFORMANCE:
 * - Implements ViewHolder pattern (required by RecyclerView)
 * - Efficient for large lists of entities
 * - Minimizes memory usage and scroll lag
 * - Better performance than ListView
 * 
 * CLICK HANDLING:
 * - Interface-based click listener for loose coupling
 * - Passes selected entity to parent activity
 * - Enables navigation to Details screen
 * 
 * USAGE:
 * - Created by DashboardActivity
 * - Attached to RecyclerView for entity display
 * - Updates automatically when entity list changes
 */
package com.example.myassssmentapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EntityRecyclerAdapter(
    private val entities: List<Entity>,
    private val onItemClick: (Entity) -> Unit
) : RecyclerView.Adapter<EntityRecyclerAdapter.EntityViewHolder>() {

    /**
     * ViewHolder class to hold references to item views
     * Required by RecyclerView for efficient view recycling
     */
    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProperty1: TextView = itemView.findViewById(R.id.tvProperty1)
        val tvProperty2: TextView = itemView.findViewById(R.id.tvProperty2)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        // Inflate the custom item layout
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_entity, 
            parent, 
            false
        )
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entities[position]
        
        // Bind data to views with fixed property labels
        holder.tvProperty1.text = "Property 1: ${entity.displayProperty1}"
        holder.tvProperty2.text = "Property 2: ${entity.displayProperty2}"
        holder.tvDescription.text = entity.displayDescription
        
        // Set click listener
        holder.itemView.setOnClickListener {
            onItemClick(entity)
        }
    }

    override fun getItemCount(): Int = entities.size
} 