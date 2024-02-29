package com.jeandarwinnewmanrios.androidjunior.imccalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.jeandarwinnewmanrios.androidjunior.R
import com.jeandarwinnewmanrios.androidjunior.imccalculator.IMCCalculatorActivity.Companion.IMC_KEY

class ResultIMCCalculatorActivity : AppCompatActivity() {

    private lateinit var tvResult:TextView
    private lateinit var tvIMC:TextView
    private lateinit var tvDescription:TextView
    private lateinit var btnRecalculate: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imccalculator)
        val result:Double = intent.extras?.getDouble(IMC_KEY)?:-1.0

        initComponent()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener{
            onBackPressed()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when(result){
            in 0.00..18.50 -> { //bajo peso

                tvResult.text = getString(R.string.bajo_peso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                tvDescription.text = getString(R.string.bajo_peso_desc)
            }
            in 18.51..24.99 -> { //peso normal

                tvResult.text = getString(R.string.normal)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.normal))
                tvDescription.text = getString(R.string.normal_desc)
            }
            in 25.00..29.99 -> { //sobrepeso

                tvResult.text = getString(R.string.sobre_peso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.sobrepeso))
                tvDescription.text = getString(R.string.sobre_peso_desc)
            }
            in 30.00..99.00 -> {//obesidad

                tvResult.text = getString(R.string.obesidad)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvDescription.text = getString(R.string.obesidad_desc)
            }
            else -> {
                tvIMC.text= getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponent() {
        tvIMC = findViewById(R.id.tvIMC)
        tvResult = findViewById(R.id.tvResult)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)

    }
}