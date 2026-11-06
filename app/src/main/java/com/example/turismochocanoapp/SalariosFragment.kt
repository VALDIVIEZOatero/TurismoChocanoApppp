package com.example.turismochocanoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class SalariosFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_salarios, container, false)

        val etHoras = view.findViewById<EditText>(R.id.etHorasTrabajadas)
        val etPago = view.findViewById<EditText>(R.id.etPagoHora)
        val btnCalcular = view.findViewById<Button>(R.id.btnCalcularSalario)
        val tvResultado = view.findViewById<TextView>(R.id.tvResultadoSalario)
        val btnVolver = view.findViewById<Button>(R.id.btnVolverDashboard)

        btnCalcular.setOnClickListener {
            val horasText = etHoras.text.toString()
            val pagoText = etPago.text.toString()

            if (horasText.isNotEmpty() && pagoText.isNotEmpty()) {
                val horas = horasText.toDouble()
                val pago = pagoText.toDouble()
                val salarioTotal = horas * pago
                tvResultado.text = String.format("Sueldo calculado: S/ %.2f", salarioTotal)
            } else {
                Toast.makeText(context, "Complete los datos de salario", Toast.LENGTH_SHORT).show()
            }
        }

        btnVolver.setOnClickListener {
            (activity as? MainActivity)?.navegarA(DashboardFragment(), "Dashboard Operativo", mostrarBarraYMenu = true)
        }

        return view
    }
}