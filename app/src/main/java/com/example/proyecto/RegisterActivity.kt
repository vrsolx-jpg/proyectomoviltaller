package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnCrearCuenta = findViewById<Button>(R.id.btnCrearCuenta)

        btnCrearCuenta.setOnClickListener {
            if (validarFormulario()) {
                val email = findViewById<TextInputEditText>(R.id.etCorreo).text.toString().trim()
                Toast.makeText(this, "¡Registro exitoso! Por favor inicia sesión", Toast.LENGTH_SHORT).show()
                
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("CORREO_PREVIO", email)
                startActivity(intent)
                finish() // Cierra el registro para que no vuelva atrás
            }
        }
    }

    private fun validarFormulario(): Boolean {
        var esValido = true

        val layoutNombre = findViewById<TextInputLayout>(R.id.layoutNombre)
        val etNombre = findViewById<TextInputEditText>(R.id.etNombre).text.toString().trim()

        val layoutCorreo = findViewById<TextInputLayout>(R.id.layoutCorreo)
        val etCorreo = findViewById<TextInputEditText>(R.id.etCorreo).text.toString().trim()

        val layoutEdad = findViewById<TextInputLayout>(R.id.layoutEdad)
        val etEdad = findViewById<TextInputEditText>(R.id.etEdad).text.toString().trim()

        val layoutCedula = findViewById<TextInputLayout>(R.id.layoutCedula)
        val etCedula = findViewById<TextInputEditText>(R.id.etCedula).text.toString().trim()

        val layoutTelefono = findViewById<TextInputLayout>(R.id.layoutTelefono)
        val etTelefono = findViewById<TextInputEditText>(R.id.etTelefono).text.toString().trim()

        val layoutContrasena = findViewById<TextInputLayout>(R.id.layoutContrasena)
        val etContrasena = findViewById<TextInputEditText>(R.id.etContrasena).text.toString()

        val cbTerminos = findViewById<CheckBox>(R.id.cbTerminos)

        val regexLetras = Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+\$")
        if (etNombre.isEmpty() || !etNombre.matches(regexLetras)) {
            layoutNombre.error = "Ingresa un nombre válido (sin números)"
            esValido = false
        } else {
            layoutNombre.error = null
        }

        if (etCorreo.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(etCorreo).matches()) {
            layoutCorreo.error = "Ingresa un correo válido"
            esValido = false
        } else {
            layoutCorreo.error = null
        }

        val edadNum = etEdad.toIntOrNull()
        if (edadNum == null || edadNum <= 0 || edadNum > 120) {
            layoutEdad.error = "Edad inválida (Máx 120)"
            esValido = false
        } else {
            layoutEdad.error = null
        }

        val regexNumeros = Regex("^[0-9]+\$")
        if (etCedula.isEmpty() || !etCedula.matches(regexNumeros)) {
            layoutCedula.error = "Cédula inválida"
            esValido = false
        } else {
            layoutCedula.error = null
        }

        if (etTelefono.isNotEmpty()) {
            if (etTelefono.length != 10 || !etTelefono.matches(regexNumeros)) {
                layoutTelefono.error = "Debe tener 10 dígitos (formato Colombia)"
                esValido = false
            } else {
                layoutTelefono.error = null
            }
        } else {
            layoutTelefono.error = null
        }

        val regexPassword = Regex("^(?=.*[0-9])(?=.*[A-Z])(?=.*[.,*@#\$%^&+=!]).{8,}\$")
        if (!etContrasena.matches(regexPassword)) {
            layoutContrasena.error = "Mín 8 caracteres, 1 mayúscula, 1 número y 1 especial (.,*)"
            esValido = false
        } else {
            layoutContrasena.error = null
        }

        if (!cbTerminos.isChecked) {
            Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_LONG).show()
            esValido = false
        }

        return esValido
    }
}
