package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mensajeBienvenida = intent.getStringExtra("MENSAJE_BIENVENIDA")
        if (mensajeBienvenida != null) {
            Toast.makeText(this, mensajeBienvenida, Toast.LENGTH_SHORT).show()
        }

        val etCorreo = findViewById<TextInputEditText>(R.id.etLoginCorreo)
        val etPass = findViewById<TextInputEditText>(R.id.etLoginPass)
        val cbRecordarme = findViewById<CheckBox>(R.id.cbRecordarme)
        val btnIniciar = findViewById<Button>(R.id.btnIniciarSesion)
        val tvIrRegistro = findViewById<TextView>(R.id.tvIrRegistro)

        // Pre-llenar correo si viene de un registro exitoso o intento previo
        val correoPrevio = intent.getStringExtra("CORREO_PREVIO")
        if (!correoPrevio.isNullOrEmpty()) {
            etCorreo.setText(correoPrevio)
        }

        btnIniciar.setOnClickListener {
            val correo = etCorreo.text.toString().trim()
            val pass = etPass.text.toString()

            if (correo.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_LONG).show()
            } else {
                val estadoRecordar = if (cbRecordarme.isChecked) "Activado" else "Desactivado"
                Toast.makeText(this, "Ingresando...\nRecordar cuenta: $estadoRecordar", Toast.LENGTH_LONG).show()
            }
        }

        tvIrRegistro.setOnClickListener {
            val correoEscrito = etCorreo.text.toString().trim()
            val intentRegistro = Intent(this, RegisterActivity::class.java)

            if (correoEscrito.isNotEmpty()) {
                intentRegistro.putExtra("CORREO_PREVIO", correoEscrito)
            }
            startActivity(intentRegistro)
        }
    }
}
