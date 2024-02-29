package com.jeandarwinnewmanrios.androidjunior

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jeandarwinnewmanrios.androidjunior.firstapp.FirstAppActivity
import com.jeandarwinnewmanrios.androidjunior.imccalculator.IMCCalculatorActivity
import com.jeandarwinnewmanrios.androidjunior.superheroapp.SuperHeroListActivity
import com.jeandarwinnewmanrios.androidjunior.todoapp.TodoActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val btnSaludar = findViewById<Button>(R.id.btnSaludar)
        val btnICMApp = findViewById<Button>(R.id.btnIMCApp)
        val btnTODO = findViewById<Button>(R.id.btnTODO)
        val btnSuperHeroList = findViewById<Button>(R.id.btnSuperHeroList)
        btnSaludar.setOnClickListener{ navigateToSaludarApp() }
        btnICMApp.setOnClickListener{ navigateToIMCApp() }
        btnTODO.setOnClickListener{ navigateToTodoApp() }
        btnSuperHeroList.setOnClickListener{ navigateToSuperListApp()}
    }

    fun navigateToIMCApp() {
        val intent = Intent(this,IMCCalculatorActivity::class.java)
        startActivity(intent)
    }

    fun navigateToSaludarApp(){
        val intent = Intent(this,FirstAppActivity::class.java)
        startActivity(intent)
    }

    fun navigateToTodoApp(){
        val intent = Intent(this, TodoActivity::class.java)
        startActivity(intent)
    }

    fun navigateToSuperListApp(){
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }
}