package com.example.turismochocanoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SalariosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salarios)

        val etHoras = findViewById<EditText>(R.id.etHorasTrabajadas)
        val etPago = findViewById<EditText>(R.id.etPagoHora)
        val btnCalcular = findViewById<Button>(R.id.btnCalcularSalario)
        val tvResultado = findViewById<TextView>(R.id.tvResultadoSalario)

        val btnNuevaReserva = findViewById<Button>(R.id.btnNuevaReserva)
        val btnEscanearQR = findViewById<Button>(R.id.btnEscanearQR)

        btnCalcular.setOnClickListener {
            val horasText = etHoras.text.toString()
            val pagoText = etPago.text.toString()

            if (horasText.isNotEmpty() && pagoText.isNotEmpty()) {
                val horas = horasText.toDouble()
                val pago = pagoText.toDouble()
                val salarioTotal = horas * pago
                tvResultado.text = String.format("Sueldo calculado: S/ %.2f", salarioTotal)
            } else {
                Toast.makeText(this, "Por favor complete los campos de salario", Toast.LENGTH_SHORT).show()
            }
        }

        btnNuevaReserva.setOnClickListener {
            Toast.makeText(this, "Aperturando módulo de venta directa...", Toast.LENGTH_SHORT).show()
        }

        btnEscanearQR.setOnClickListener {
            Toast.makeText(this, "Activando cámara para escaneo de QR...", Toast.LENGTH_SHORT).show()
        }
    }
}