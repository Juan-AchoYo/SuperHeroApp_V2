package com.juan.superheroapp_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.juan.superheroapp_v2.DetailActivity.Companion.SUPER_HERO_ID
import com.juan.superheroapp_v2.databinding.ActivityAppearanceBinding
import com.juan.superheroapp_v2.databinding.ActivityConnectionsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ConnectionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConnectionsBinding
    private lateinit var superHeroId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnectionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        superHeroId = intent.getStringExtra(SUPER_HERO_ID).orEmpty()
        getConnections()
    }

    private fun getConnections() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit().create(ApiService::class.java).getConnections(superHeroId)
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    binding.tvName.text = response.body()!!.name
                    binding.tvGroupAffiliation.text = response.body()!!.groupAffiliation
                    binding.tvRelatives.text = response.body()!!.relatives
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