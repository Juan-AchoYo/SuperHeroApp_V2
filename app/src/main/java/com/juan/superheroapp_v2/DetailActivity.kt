package com.juan.superheroapp_v2

import android.content.Intent
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
        initUI()
    }

    private fun initUI() {
        binding.btnBiography.setOnClickListener { navigateToBiography() }
        binding.btnAppearance.setOnClickListener { navigateToAppearance() }
        binding.btnConnections.setOnClickListener { navigateToConnections() }
        binding.btnWork.setOnClickListener { navigateToWork() }
        binding.btnPowerStats.setOnClickListener { navigateToPowerStats() }
    }

    private fun navigateToPowerStats() {
        val intent = Intent(this, PowerStatsActivity::class.java)
        intent.putExtra(SUPER_HERO_ID, superHeroId)
        startActivity(intent)
    }

    private fun navigateToWork() {
        val intent = Intent(this, WorkActivity::class.java)
        intent.putExtra(SUPER_HERO_ID, superHeroId)
        startActivity(intent)
    }

    private fun navigateToConnections() {
        val intent = Intent(this, ConnectionsActivity::class.java)
        intent.putExtra(SUPER_HERO_ID, superHeroId)
        startActivity(intent)
    }

    private fun navigateToAppearance() {
        val intent = Intent(this, AppearanceActivity::class.java)
        intent.putExtra(SUPER_HERO_ID, superHeroId)
        startActivity(intent)
    }

    private fun navigateToBiography() {
        val intent = Intent(this, BiographyActivity::class.java)
        intent.putExtra(SUPER_HERO_ID, superHeroId)
        startActivity(intent)

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