package com.juan.superheroapp_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.juan.superheroapp_v2.databinding.ActivityConnectionsBinding
import com.juan.superheroapp_v2.databinding.ActivityWorkBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WorkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkBinding
    private lateinit var superHeroId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        superHeroId = intent.getStringExtra(DetailActivity.SUPER_HERO_ID).orEmpty()
        getWork()
    }

    private fun getWork() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit().create(ApiService::class.java).getWork(superHeroId)
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    binding.tvName.text = response.body()!!.name
                    binding.tvOccupation.text = response.body()!!.occupation
                    binding.tvBase.text = response.body()!!.base
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