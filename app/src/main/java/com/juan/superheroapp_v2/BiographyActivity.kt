package com.juan.superheroapp_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.juan.superheroapp_v2.DetailActivity.Companion.SUPER_HERO_ID
import com.juan.superheroapp_v2.databinding.ActivityBiographyBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BiographyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBiographyBinding
    private lateinit var superHeroId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiographyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        superHeroId = intent.getStringExtra(SUPER_HERO_ID).orEmpty()
        getBiography()
    }

    private fun getBiography() {
        CoroutineScope(Dispatchers.IO).launch {
            val biobraphyResponse: Response<BiographyResponse> =
                getRetrofit().create(ApiService::class.java).getBiography(superHeroId)
            if (biobraphyResponse.isSuccessful) {
                withContext(Dispatchers.Main) {
                    binding.tvName.text = biobraphyResponse.body()!!.name
                    binding.tvFullName.text = biobraphyResponse.body()!!.fullName
                    binding.tvAlterEgos.text = biobraphyResponse.body()!!.alterEgos
                    binding.tvAliases.text = biobraphyResponse.body()!!.aliases.toString()
                    binding.tvPlaceOfBirth.text = biobraphyResponse.body()!!.placeOfBirth
                    binding.tvFirstAppearance.text = biobraphyResponse.body()!!.firstAppearance
                    binding.tvAlignment.text = biobraphyResponse.body()!!.alignment
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