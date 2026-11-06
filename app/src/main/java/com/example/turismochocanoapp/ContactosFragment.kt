package com.example.turismochocanoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class ContactosFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contactos, container, false)

        val etNombre = view.findViewById<EditText>(R.id.etNombre)
        val etCorreo = view.findViewById<EditText>(R.id.etCorreo)
        val etMensaje = view.findViewById<EditText>(R.id.etMensaje)
        val btnEnviar = view.findViewById<Button>(R.id.btnEnviarConsulta)
        val btnVolver = view.findViewById<Button>(R.id.btnVolverDashboard)

        btnEnviar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val mensaje = etMensaje.text.toString().trim()

            if (nombre.isEmpty() || correo.isEmpty() || mensaje.isEmpty()) {
                Toast.makeText(context, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "¡Consulta enviada con éxito!", Toast.LENGTH_LONG).show()
                etNombre.text.clear()
                etCorreo.text.clear()
                etMensaje.text.clear()
            }
        }

        btnVolver.setOnClickListener {
            (activity as? MainActivity)?.navegarA(DashboardFragment(), "Dashboard Operativo", mostrarBarraYMenu = true)
        }

        return view
    }
}