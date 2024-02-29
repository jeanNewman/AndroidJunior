package com.jeandarwinnewmanrios.androidjunior.sampledata

fun main() {


    data class GRADES(
        val converTo: String,
        val initGrade: Double,
        val initUnit: String,
        val finalUnit: String,
        ){
        fun celsiusToFahrenheit(celsius: Double): Double {
            return (9.0 / 5.0 * celsius) + 32
        }

        fun kelvinToCelsius(kelvin: Double): Double {
            return kelvin - 273.15
        }

        fun fahrenheitToKelvin(fahrenheit: Double): Double {
            return (5.0 / 9.0 * (fahrenheit - 32)) + 273.15
        }
    }

    val allGrades = arrayOf(
        GRADES(
            converTo = "C2F",
            initGrade = 27.0,
            initUnit = "Celcius",
            finalUnit = "Fahrenheit"

        ),
        GRADES(
            converTo = "K2C",
            initGrade = 350.0,
            initUnit = "Kelvin",
            finalUnit = "Celcius"
        ),
        GRADES(
            converTo = "F2K",
            initGrade = 10.0,
            initUnit = "Fahrenheit",
            finalUnit = "Kelvin"
        )
    )
    for (eachGrade in allGrades){
      printFinalTemperature(eachGrade.initGrade,eachGrade.initUnit, eachGrade.finalUnit,eachGrade.converTo,
                conversionFormula = ::formulaToConvert)

    }
}

fun formulaToConvert(initialMeasurement: Double, toConvert: String) : Double{
    return when(toConvert){
        "C2F"-> (initialMeasurement * 9/5) +32
        "K2C"-> (initialMeasurement  - 273.15)
        else -> (initialMeasurement - 32) * 5/9 + 273.15
    }
}
fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    toConvert: String,
    conversionFormula: (Double, String) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement,toConvert)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}