package com.example.turismochocanoapp

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.util.Locale

class DashboardFragment : Fragment() {

    private lateinit var contenedorTarjetas: LinearLayout
    private lateinit var etBuscar: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        etBuscar = view.findViewById(R.id.etBuscarGlobal)
        contenedorTarjetas = view.findViewById(R.id.contenedorTarjetas)
        val btnNuevaReserva = view.findViewById<Button>(R.id.btnNuevaReserva)
        val btnEscanearQR = view.findViewById<Button>(R.id.btnEscanearQR)
        etBuscar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filtrarReservas(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        btnNuevaReserva.setOnClickListener {
            (activity as? MainActivity)?.navegarA(ReservaFragment(), "Nueva Reserva", true)
        }

        btnEscanearQR.setOnClickListener {
            Toast.makeText(context, "Activando cámara para escaneo de QR...", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        filtrarReservas(etBuscar.text.toString())
    }

    private fun filtrarReservas(queryText: String) {
        val query = queryText.lowercase(Locale.getDefault()).trim()
        val filtradas = ReservaRepository.listaReservas.filter {
            it.nombre.lowercase().contains(query) ||
                    it.pnr.lowercase().contains(query) ||
                    it.dni.lowercase().contains(query)
        }
        mostrarReservas(filtradas)
    }

    private fun mostrarReservas(lista: List<ReservaItem>) {
        if (!::contenedorTarjetas.isInitialized) return
        contenedorTarjetas.removeAllViews()
        val context = requireContext()

        for (reserva in lista) {
            val cardLayout = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                setBackgroundResource(R.drawable.card_background)
                setPadding(30, 30, 30, 30)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 0, 0, 16) }
            }

            val headerLayout = RelativeLayout(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }

            val tvNombre = TextView(context).apply {
                text = "${reserva.nombre} (PNR: ${reserva.pnr})"
                setTextColor(Color.parseColor("#C8102E"))
                textSize = 16f
                setTypeface(null, android.graphics.Typeface.BOLD)
            }

            val tvEstado = TextView(context).apply {
                text = reserva.estado
                setTextColor(Color.WHITE)
                textSize = 11f
                setTypeface(null, android.graphics.Typeface.BOLD)
                setPadding(16, 6, 16, 6)
                setBackgroundColor(Color.parseColor(reserva.colorEstado))
                layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                ).apply { addRule(RelativeLayout.ALIGN_PARENT_END) }
            }

            headerLayout.addView(tvNombre)
            headerLayout.addView(tvEstado)
            cardLayout.addView(headerLayout)

            val tvDetalle = TextView(context).apply {
                text = "Ruta: ${reserva.ruta} | Asiento: ${reserva.asiento}\nDNI: ${reserva.dni}"
                setTextColor(Color.parseColor("#333333"))
                textSize = 14f
                setPadding(0, 8, 0, 4)
            }
            cardLayout.addView(tvDetalle)

            val tvPrecio = TextView(context).apply {
                text = "Precio Total: S/ 90.00"
                setTextColor(Color.parseColor("#333333"))
                textSize = 14f
                setTypeface(null, android.graphics.Typeface.BOLD)
            }
            cardLayout.addView(tvPrecio)
            val btnLayout = LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.END
                setPadding(0, 14, 0, 0)
            }

            val btnVer = Button(context).apply {
                text = "Ver"
                textSize = 12f
                setTextColor(Color.WHITE)
                setBackgroundResource(R.drawable.btn_blue)
                layoutParams = LinearLayout.LayoutParams(130, 85).apply { setMargins(0, 0, 8, 0) }
                setOnClickListener { Toast.makeText(context, "Ver reserva ${reserva.pnr}", Toast.LENGTH_SHORT).show() }
            }

            val btnEditar = Button(context).apply {
                text = "Editar"
                textSize = 12f
                setTextColor(Color.WHITE)
                setBackgroundResource(R.drawable.btn_orange)
                layoutParams = LinearLayout.LayoutParams(130, 85).apply { setMargins(0, 0, 8, 0) }
                setOnClickListener { Toast.makeText(context, "Editar reserva ${reserva.pnr}", Toast.LENGTH_SHORT).show() }
            }

            val btnEliminar = Button(context).apply {
                text = "Eliminar"
                textSize = 12f
                setTextColor(Color.WHITE)
                setBackgroundResource(R.drawable.btn_red)
                layoutParams = LinearLayout.LayoutParams(150, 85)
                setOnClickListener {
                    ReservaRepository.listaReservas.remove(reserva)
                    filtrarReservas(etBuscar.text.toString())
                    Toast.makeText(context, "Reserva eliminada", Toast.LENGTH_SHORT).show()
                }
            }

            btnLayout.addView(btnVer)
            btnLayout.addView(btnEditar)
            btnLayout.addView(btnEliminar)
            cardLayout.addView(btnLayout)
            contenedorTarjetas.addView(cardLayout)
        }
    }
}