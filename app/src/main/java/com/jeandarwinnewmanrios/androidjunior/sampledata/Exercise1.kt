package com.jeandarwinnewmanrios.androidjunior.sampledata

fun main() {
    val morningNotification = 55
    val eveningNotification = 135

    println(printNotificationSumary(morningNotification))
  //  println(printNotificationSumary(eveningNotification))
}

fun printNotificationSumary(numberOfMessages:Int) = when (numberOfMessages > 99) {
        false ->  "You Have $numberOfMessages notification"
        true -> "Your phone is blowin Up! You have 99+ notification"
    }


