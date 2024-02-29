package com.jeandarwinnewmanrios.androidjunior.superheroapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeandarwinnewmanrios.androidjunior.databinding.ActivitySuperHeroListBinding
import com.jeandarwinnewmanrios.androidjunior.superheroapp.DetailSuperActivity.Companion.EXTRA_ID
import com.jeandarwinnewmanrios.androidjunior.superheroapp.constructor.SuperHeroController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class SuperHeroListActivity : AppCompatActivity() {
    private val baseUrl = "https://superheroapi.com/"

    private lateinit var binding: ActivitySuperHeroListBinding
    val retrofitClient = SuperHeroController(baseUrl, this)
    private lateinit var retrofit: Retrofit

    private lateinit var superHeroAdapter: SuperHeroAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_super_hero_list)
        setContentView(binding.root)
        retrofit = retrofitClient.getRetrofit()
        initUI()

    }

    private fun initUI() {

        binding.svSuperList.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                retrofitClient.searchByName(query.orEmpty(),binding, retrofit,superHeroAdapter)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })
        superHeroAdapter = SuperHeroAdapter{navigateToDetail(it)}
        binding.rvSuperList.setHasFixedSize(true)
        binding.rvSuperList.layoutManager = LinearLayoutManager(this)
        binding.rvSuperList.adapter = superHeroAdapter
    }

   /* private fun searchByName(query: String) {
        binding.pbsuperhero.isVisible =  true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<Root> = retrofit.create(ApiService::class.java).getSuperheroes(query)
            if (myResponse.isSuccessful){
                Log.i("jean","funciona")
                val response: Root? = myResponse.body()
                if(response != null){
                    Log.i("darwin",response.toString())
                    runOnUiThread{
                        superHeroAdapter.updateList(response.results)
                        binding.pbsuperhero.isVisible =  false
                    }
                }

            }
            else{
                Log.i("jean","No funciona")
            }
        }

    }
*/
  /*  private fun getRetrofit(): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/

    private fun navigateToDetail(id:String){
        val intent = Intent(this,DetailSuperActivity::class.java)
        intent.putExtra(EXTRA_ID,id)
        startActivity(intent)
    }
}

