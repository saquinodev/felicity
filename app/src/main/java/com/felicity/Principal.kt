package com.felicity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_principal.*

class Principal : AppCompatActivity() {

    private var button:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        singOut()
        click_Alquileres()
        click_Clientes()
        click_Gastos()
        click_Estadisticas()
        click_Espacio()
    }

    fun click_Alquileres(){
        alquileres.setOnClickListener(){
            startActivity(Intent(this,AlquileresActivity::class.java))
        }
    }fun click_Clientes(){
        clientes.setOnClickListener(){
            startActivity(Intent(this, ClientesActivity::class.java))
        }
    }fun click_Gastos(){
        gastos.setOnClickListener(){
            startActivity(Intent(this,GastosActivity::class.java))
        }
    }fun click_Estadisticas(){
        estadisticas.setOnClickListener(){
            startActivity(Intent(this,EstadisticasActivity::class.java))
        }
    }fun click_Espacio(){
        espacio.setOnClickListener(){
            startActivity(Intent(this,EspacioActivity::class.java))
        }
    }


    private fun singOut(){
        btn_logout.setOnClickListener{
            AuthUI.getInstance().signOut(this).addOnSuccessListener {
                startActivity(Intent(this,LoginActivity::class.java))
                Toast.makeText(this,"Hasta pronto!",Toast.LENGTH_SHORT).show()
                finish()

            }.addOnFailureListener{
                Toast.makeText(this,"Ocurrio un error ${it.message}",Toast.LENGTH_SHORT).show()
            }
        }
    }

}