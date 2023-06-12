package com.juan.superheroapp_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.juan.superheroapp_v2.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {
    companion object {
        const val SUPER_HERO_ID = "super_hero_id"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var superHeroId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        superHeroId = intent.getStringExtra(SUPER_HERO_ID).orEmpty()
        getSuperHeroInformation(superHeroId)
    }

    private fun getSuperHeroInformation(superHeroId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<SuperHeroDetailResponse> =
                getRetrofit().create(ApiService::class.java).getSuperHeroDetail(superHeroId)
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    createUI(response.body()!!)
                }
            }
        }
    }

    private fun createUI(body: SuperHeroDetailResponse) {
        binding.tvSuperHeroDetailTitle.text = body.name
        Picasso.get().load(body.image.url).into(binding.ivSuperHeroDetail)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}