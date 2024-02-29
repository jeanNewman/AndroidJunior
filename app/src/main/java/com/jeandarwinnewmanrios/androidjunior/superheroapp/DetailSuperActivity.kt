package com.jeandarwinnewmanrios.androidjunior.superheroapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.mikephil.charting.animation.Easing
import com.jeandarwinnewmanrios.androidjunior.databinding.ActivityDetailSuperBinding
import com.jeandarwinnewmanrios.androidjunior.superheroapp.models.ResultInfo
import com.jeandarwinnewmanrios.androidjunior.superheroapp.constructor.SuperHeroController
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import com.jeandarwinnewmanrios.androidjunior.superheroapp.models.MyValueFormatter
import com.jeandarwinnewmanrios.androidjunior.superheroapp.models.MyXAxisValueF


import com.squareup.picasso.Picasso
import retrofit2.Retrofit

class DetailSuperActivity : AppCompatActivity() {
    private val baseUrl = "https://superheroapi.com/"

    private lateinit var binding: ActivityDetailSuperBinding
    private val retrofitClient = SuperHeroController(baseUrl, this)
    private lateinit var retrofit: Retrofit


     /*val mtfLight: Typeface by lazy {
        Typeface.createFromAsset(assets, "OpenSans-Light.ttf")
    }*/

    companion object{
        const val EXTRA_ID = "extra_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = retrofitClient.getRetrofit()
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()

        getSuperHeroInformation(id)
    }

    private fun getSuperHeroInformation(id: String) {
        retrofitClient.superHeroInformation(id,this, retrofit)
    }

    fun createUIDetails(body: ResultInfo) {
      try {
          val radarChart = binding.radarChart

          //radarChart.setBackgroundColor(Color.rgb(60, 65, 82));
          radarChart.description.isEnabled = false;

          radarChart.webLineWidth = 10f;
          radarChart.webColor = Color.LTGRAY;
          radarChart.webLineWidthInner = 2f;
          radarChart.webColorInner = Color.LTGRAY;
          radarChart.webAlpha = 100;
          radarChart.cameraDistance = 0.1f

          Picasso.get().load(body.image.url).into(binding.ivSuperHeroDetail)
            binding.tvSuperHeroName.text = body.name
            binding.tvRealName.text = body.biography.fullName


          val powerStatsValues: List<Int> = listOf(
              body.powerstats.intelligence?.toIntOrNull() ?: 0,
              body.powerstats.strength?.toIntOrNull() ?: 0,
              body.powerstats.speed?.toIntOrNull() ?: 0,
              body.powerstats.durability?.toIntOrNull() ?: 0,
              body.powerstats.power?.toIntOrNull() ?: 0,
              body.powerstats.combat?.toIntOrNull() ?: 0
          )


          val scores: List<Int> = listOf(18, 26, 35, 40, 48) //body.scores // Assuming scores come from ResultInfo
          val radarData = createRadarData(powerStatsValues)
          radarChart.data = radarData
          setupRadarChart(radarChart)
          radarChart.invalidate()
      } catch (e: Exception ) {
          Log.i("trucutru",e.toString())
      }

    }

    private fun createRadarData(scores: List<Int>): RadarData {
        val entries = ArrayList<RadarEntry>()

        for (i in scores.indices) {
            entries.add(RadarEntry(scores[i].toFloat()))
        }

        val dataSet = RadarDataSet(entries, "").apply {
            color = Color.BLUE
            setDrawFilled(true)
        }

        val dataSets = ArrayList<IRadarDataSet>().apply {
            add(dataSet)
        }

        val data = RadarData(dataSets).apply {
           // setValueTypeface(mtfLight)
            setValueTextSize(20f)
        }

       data.setValueFormatter(MyValueFormatter())

        return data

        }

    private fun setupRadarChart(chart: RadarChart) {
        val powerstats: Array<String> = arrayOf("intelligence", "strength", "speed", "durability", "power", "combat")

         val l: Legend = chart.getLegend();
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP;
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER;
        l.orientation = Legend.LegendOrientation.HORIZONTAL;
        l.setDrawInside(false);
        //l.typeface = tfLight;
        l.xEntrySpace = 7f;
        l.yEntrySpace = 5f;
        l.textColor = Color.WHITE;


        val xAxis = chart.xAxis
        xAxis.xOffset = 200f
        xAxis.yOffset = 0f
        //xAxis.typeface = mtfLight
        xAxis.textSize = 12f
        xAxis.valueFormatter = MyXAxisValueF(powerstats)
        XAxisPosition.BOTH_SIDED


        val yAxis = chart.yAxis
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 100f
       // yAxis.typeface = mTfLight
        yAxis.textSize = 12f
        yAxis.setLabelCount(6, false)
        yAxis.setDrawLabels(false)

        chart.legend.isEnabled = false
        chart.description.isEnabled = false
        chart.animateXY(1400, 1400, Easing.EaseInOutQuad, Easing.EaseInOutQuad)
    }


}