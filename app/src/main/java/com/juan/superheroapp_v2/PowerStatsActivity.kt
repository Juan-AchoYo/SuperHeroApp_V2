package com.juan.superheroapp_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.juan.superheroapp_v2.databinding.ActivityConnectionsBinding
import com.juan.superheroapp_v2.databinding.ActivityPowerStatsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PowerStatsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPowerStatsBinding
    private lateinit var superHeroId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPowerStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        superHeroId = intent.getStringExtra(DetailActivity.SUPER_HERO_ID).orEmpty()
        getPowerStats()
    }

    private fun getPowerStats() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit().create(ApiService::class.java).getPowerStats(superHeroId)
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    binding.tvName.text = response.body()!!.name
                    binding.tvIntelligence.text = response.body()!!.intelligence
                    binding.tvStrength.text = response.body()!!.strength
                    binding.tvSpeed.text = response.body()!!.speed
                    binding.tvDurability.text = response.body()!!.durability
                    binding.tvPower.text = response.body()!!.power
                    binding.tvCombat.text = response.body()!!.combat
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