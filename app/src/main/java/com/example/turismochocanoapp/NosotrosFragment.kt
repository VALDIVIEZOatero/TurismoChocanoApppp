package com.example.turismochocanoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class NosotrosFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nosotros, container, false)

        val btnVolver = view.findViewById<Button>(R.id.btnVolverInicio)
        btnVolver.setOnClickListener {
            (activity as? MainActivity)?.navegarA(DashboardFragment(), "Dashboard Operativo", mostrarBarraYMenu = true)
        }

        return view
    }
}