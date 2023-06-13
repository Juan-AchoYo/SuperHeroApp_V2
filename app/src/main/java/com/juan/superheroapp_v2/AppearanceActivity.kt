package com.juan.superheroapp_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.juan.superheroapp_v2.DetailActivity.Companion.SUPER_HERO_ID
import com.juan.superheroapp_v2.databinding.ActivityAppearanceBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class AppearanceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppearanceBinding
    private lateinit var superHeroId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppearanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        superHeroId = intent.getStringExtra(SUPER_HERO_ID).orEmpty()
        getAppearance()

    }

    private fun getAppearance() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit().create(ApiService::class.java).getAppearance(superHeroId)
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    binding.tvName.text = response.body()!!.name
                    binding.tvGender.text = response.body()!!.gender
                    binding.tvRace.text = response.body()!!.race
                    binding.tvHeight.text = response.body()!!.height.toString()
                    binding.tvWeight.text = response.body()!!.weight.toString()
                    binding.tvEyeColor.text = response.body()!!.eyeColor
                    binding.tvHairColor.text = response.body()!!.hairColor
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}