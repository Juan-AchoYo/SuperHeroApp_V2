package com.juan.superheroapp_v2

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.juan.superheroapp_v2.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemSuperheroBinding.bind(view)

    fun render(superHeroItem: SuperHeroItem) {
        binding.tvSuperHero.text = superHeroItem.name

        Picasso.get().load(superHeroItem.image.url).into(binding.ivSuperHero)


    }
}