package com.jeandarwinnewmanrios.androidjunior.sampledata

fun main(){
    val child = 5
    val adult = 28
    val senior = 87

    val isMonday = false

    println("the movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}")
    println("the movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}")
    println("the movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}")

}

fun ticketPrice(age: Int, isMonday: Boolean) : Int {
    return when(age){
        in 0 .. 12 -> 15
        in 13 .. 60 -> if(isMonday) 25 else 30
        in 61 .. 100 -> 20
        else -> -1
    }
}