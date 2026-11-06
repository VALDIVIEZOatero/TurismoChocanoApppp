package com.example.turismochocanoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val btnIrSalarios = view.findViewById<Button>(R.id.btnIrSalarios)

        btnIrSalarios.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email == "admin@turismochocano.com" && password == "123456") {
                Toast.makeText(context, "¡Bienvenido al Dashboard!", Toast.LENGTH_SHORT).show()
                (activity as? MainActivity)?.iniciarSesionExitoso()
            } else {
                Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }
}