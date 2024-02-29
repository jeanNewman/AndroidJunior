package com.jeandarwinnewmanrios.androidjunior.superheroapp.models
import android.util.Log
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter


import com.github.mikephil.charting.utils.ViewPortHandler
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.text.DecimalFormat

class MyValueFormatter : ValueFormatter() {
    private val mFormat: DecimalFormat = DecimalFormat("###,###,##0.0") // usa un decimal

    override fun getFormattedValue(value: Float): String {
        // Escribe tu lógica aquí

            return mFormat.format(value.toDouble()) // por ejemplo, agrega un signo de dólar

    }
}

class MyXAxisValueF(private val mValues: Array<String>) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        // "value" represents the position of the label on the axis (x or y)
        return if (mValues.isNotEmpty() && value.toInt() >= 0 && value.toInt() < mValues.size) {
            mValues[value.toInt()]
        } else {
            ""
        }
    }
}
