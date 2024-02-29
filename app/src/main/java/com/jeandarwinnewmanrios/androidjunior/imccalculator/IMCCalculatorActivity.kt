package com.jeandarwinnewmanrios.androidjunior.imccalculator

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.jeandarwinnewmanrios.androidjunior.R
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow
import kotlin.math.roundToLong


class IMCCalculatorActivity : AppCompatActivity() {
    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var tvHeight:TextView
    private lateinit var rsHeight:RangeSlider
    private lateinit var tvWeight:TextView
    private lateinit var btnSubstractWeight:FloatingActionButton
    private lateinit var btnAddWeight: FloatingActionButton
    private lateinit var tvAge:TextView
    private lateinit var btnSubstractAge:FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private lateinit var btnCalculate: Button
    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 70
    private var currentAge: Int = 25
    private var currentHeight: Double = 120.0

    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imccalculator)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubstractWeight = findViewById(R.id.btnSubtractWeight)
        btnAddWeight = findViewById(R.id.btnPlusWeight)
        tvAge = findViewById(R.id.tvAge)
        btnSubstractAge = findViewById(R.id.btnSubtractAge)
        btnAddAge = findViewById(R.id.btnPlusAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }


    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }

    @SuppressLint("SetTextI18n")
    private fun initListeners() {
        viewMale.setOnClickListener{
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener{
            changeGender()
            setGenderColor()
        }

        rsHeight.addOnChangeListener { _, value, _ ->

            currentHeight = value.toDouble()
            tvHeight.text = String.format("%.0f", value) + " cm"
        }

        btnSubstractWeight.setOnClickListener{
            try {
                currentWeight -= 1
               setWeight()
            } catch (nfe: NumberFormatException) {
                // not a valid int
            }
        }

        btnAddWeight.setOnClickListener{
            try {
                currentWeight += 1
                setWeight()
            } catch (nfe: NumberFormatException) {
                // not a valid int
            }
        }

        btnSubstractAge.setOnClickListener{
            try {
                currentAge -= 1
                setAge()
            } catch (nfe: NumberFormatException) {
                // not a valid int
            }
        }

        btnAddAge.setOnClickListener{
            try {
                currentAge += 1
                setAge()
            } catch (nfe: NumberFormatException) {
                // not a valid int
            }
        }

        btnCalculate.setOnClickListener {
           val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this,ResultIMCCalculatorActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val imc = BigDecimal((currentWeight.toDouble() / (currentHeight/100).pow(2.0))).setScale(2, RoundingMode.HALF_UP).toDouble()

        Log.i("jeannewman", "calculateIMC: el imc es: $currentWeight / ($currentHeight * $currentHeight) = $imc")
        return imc.toDouble()
    }

    private fun setWeight(){
        tvWeight.text = currentWeight.toString()
    }

    private fun setAge(){
        tvAge.text = currentAge.toString()
    }

    private fun changeGender(){
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {

        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }


    private fun getBackgroundColor(isSelectedComponent:Boolean): Int {
        val colorRef = if(isSelectedComponent){
            R.color.background_component_selected
        }
        else{
            R.color.background_component
        }

        return ContextCompat.getColor(this,colorRef)
    }




}