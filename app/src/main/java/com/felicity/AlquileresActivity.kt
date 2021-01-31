package com.felicity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_alquileres.*
import kotlinx.android.synthetic.main.activity_principal.*

class AlquileresActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alquileres)
        click_Reservar()
        click_Consultar()

    }
    fun click_Reservar(){
        reservar.setOnClickListener(){
            startActivity(Intent(this,CalendarioActivity::class.java)) //antes ReservarActivity
        }
    }fun click_Consultar(){
        consultar.setOnClickListener(){
            startActivity(Intent(this,ConsulActivity::class.java))
        }
    }
}