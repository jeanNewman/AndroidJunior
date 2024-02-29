package com.jeandarwinnewmanrios.androidjunior.todoapp

data class Task(
    val name: String,
    val category: String,
    var isSelected: Boolean = false

)