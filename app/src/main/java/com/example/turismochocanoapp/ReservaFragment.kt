package com.example.turismochocanoapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.util.*

class ReservaFragment : Fragment() {

    private var fechaViajeSeleccionada: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reserva, container, false)

        val etDni = view.findViewById<EditText>(R.id.etDniPasajero)
        val etNombre = view.findViewById<EditText>(R.id.etNombrePasajero)
        val spOrigen = view.findViewById<Spinner>(R.id.spOrigen)
        val spDestino = view.findViewById<Spinner>(R.id.spDestino)
        val btnFecha = view.findViewById<Button>(R.id.btnSeleccionarFecha)
        val tvFecha = view.findViewById<TextView>(R.id.tvFechaSeleccionada)
        val spAsiento = view.findViewById<Spinner>(R.id.spAsiento)
        val btnConfirmar = view.findViewById<Button>(R.id.btnConfirmarReserva)
        val btnVolver = view.findViewById<Button>(R.id.btnVolverDashboard)
        val ciudades = arrayOf("Uchiza", "Lima")
        val adapterCiudades = ArrayAdapter(requireContext(), R.layout.spinner_item, ciudades)
        adapterCiudades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spOrigen.adapter = adapterCiudades
        spDestino.adapter = adapterCiudades
        spDestino.setSelection(1)
        val asientos = (1..10).map { "Asiento N° $it" }.toTypedArray()
        val adapterAsientos = ArrayAdapter(requireContext(), R.layout.spinner_item, asientos)
        adapterAsientos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spAsiento.adapter = adapterAsientos

        btnFecha.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    fechaViajeSeleccionada = "$dayOfMonth/${month + 1}/$year"
                    tvFecha.text = "Fecha seleccionada: $fechaViajeSeleccionada"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.datePicker.minDate = calendar.timeInMillis
            val maxCalendar = Calendar.getInstance()
            maxCalendar.set(2026, Calendar.DECEMBER, 31)
            datePicker.datePicker.maxDate = maxCalendar.timeInMillis
            datePicker.show()
        }

        btnConfirmar.setOnClickListener {
            val dni = etDni.text.toString().trim()
            val nombre = etNombre.text.toString().trim()
            val origen = spOrigen.selectedItem.toString()
            val destino = spDestino.selectedItem.toString()
            val asiento = spAsiento.selectedItem.toString()

            if (dni.length != 8) {
                Toast.makeText(context, "El DNI debe tener exactamente 8 dígitos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (nombre.isEmpty()) {
                Toast.makeText(context, "Ingrese los nombres y apellidos completos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (origen == destino) {
                Toast.makeText(context, "El origen y destino no pueden ser iguales", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (fechaViajeSeleccionada.isEmpty()) {
                Toast.makeText(context, "Seleccione la fecha de viaje", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevoPnr = "TC-00${ReservaRepository.listaReservas.size + 1}"
            val nuevaReserva = ReservaItem(
                nombre = nombre,
                pnr = nuevoPnr,
                dni = dni,
                ruta = "$origen ➔ $destino",
                asiento = asiento,
                estado = "CONFIRMADO",
                colorEstado = "#27AE60"
            )
            ReservaRepository.listaReservas.add(0, nuevaReserva)

            Toast.makeText(context, "¡Reserva guardada y boleto emitido con éxito!", Toast.LENGTH_LONG).show()
            (activity as? MainActivity)?.navegarA(DashboardFragment(), "Dashboard Operativo", true)
        }

        btnVolver.setOnClickListener {
            (activity as? MainActivity)?.navegarA(DashboardFragment(), "Dashboard Operativo", true)
        }

        return view
    }
}