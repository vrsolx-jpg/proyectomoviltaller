package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnHomeLogin = findViewById<Button>(R.id.btnHomeLogin)
        val btnHomeRegistro = findViewById<Button>(R.id.btnHomeRegistro)

        btnHomeLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("MENSAJE_BIENVENIDA", "¡Hola! Ingresa tus datos")
            startActivity(intent)
        }

        btnHomeRegistro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
