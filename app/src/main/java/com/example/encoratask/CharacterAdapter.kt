package com.example.encoratask

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CharacterAdapter(val characters : MutableList<Character>) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_grid_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        Picasso.get().load(character.image).into(holder.image)
        holder.name.text = character.name
        holder.status.text = character.status
        holder.status.setTextColor(getStatusTextColor(character.status))
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    private fun getStatusTextColor(status : String) : Int {
        if (status.equals("alive", ignoreCase = true)) {
            return Color.GREEN
        }
        if (status.equals("dead", ignoreCase = true)) {
            return Color.RED
        }
        return Color.BLACK
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image: ImageView = itemView.findViewById(R.id.image)
        var name : TextView = itemView.findViewById(R.id.name);
        var status : TextView = itemView.findViewById(R.id.status);

    }
}