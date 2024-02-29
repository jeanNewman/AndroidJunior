package com.jeandarwinnewmanrios.androidjunior.superheroapp.constructor
import android.app.Activity
import android.util.Log
import androidx.core.view.isVisible
//import com.jeandarwinnewmanrios.androidjunior.databinding.ActivityDetailSuperBinding
import com.jeandarwinnewmanrios.androidjunior.databinding.ActivitySuperHeroListBinding
import com.jeandarwinnewmanrios.androidjunior.superheroapp.ApiService
import com.jeandarwinnewmanrios.androidjunior.superheroapp.DetailSuperActivity
import com.jeandarwinnewmanrios.androidjunior.superheroapp.models.Root
import com.jeandarwinnewmanrios.androidjunior.superheroapp.SuperHeroAdapter
import com.jeandarwinnewmanrios.androidjunior.superheroapp.models.ResultInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroController( private val baseUrl: String, private val activity: Activity) {

 //   private lateinit var retrofit: Retrofit

   // private lateinit var superHeroAdapter: SuperHeroAdapter

   // private lateinit var detailSuperActivity: DetailSuperActivity

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun searchByName(query: String,
                     binding: ActivitySuperHeroListBinding,
                     retrofit: Retrofit,
                     superHeroAdapter: SuperHeroAdapter) {
        binding.pbsuperhero.isVisible
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<Root> = retrofit
                .create(ApiService::class.java)
                .getSuperheroes(query)
            if (myResponse.isSuccessful) {
                val response: Root? = myResponse.body()
                if (response != null) {
                    activity.runOnUiThread {
                        superHeroAdapter.updateList(response.results)
                        binding.pbsuperhero.isVisible = false
                    }
                }
            }
            else{
                Log.i("jean","No funciona")
            }

        }

    }

    fun superHeroInformation(id:String,
                             datailContext: DetailSuperActivity,
                             //binding: ActivityDetailSuperBinding,
                             retrofit: Retrofit                             ){


        CoroutineScope(Dispatchers.IO).launch {

            val myResponse: Response<ResultInfo> = retrofit
                .create(ApiService::class.java)
                .getSuperheroesById(id)

            if(myResponse.isSuccessful){
                val response: ResultInfo? = myResponse.body()
                if (response != null) {
                    activity.runOnUiThread {
                        datailContext.createUIDetails(myResponse.body()!!)

                    }
                }
            }
            else{
                Log.i("trucutru","No funciona")
            }
        }
    }

}