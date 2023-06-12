package com.juan.superheroapp_v2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SuperHeroAdapter(var superHeroList: List<SuperHeroItem> = emptyList()) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        return SuperHeroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.render(superHeroList[position])
    }

    override fun getItemCount() = superHeroList.size

    fun updateList(results: List<SuperHeroItem>) {
        superHeroList = results
        notifyDataSetChanged()
    }
}