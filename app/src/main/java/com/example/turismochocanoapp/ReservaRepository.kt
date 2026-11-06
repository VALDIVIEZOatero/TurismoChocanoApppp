package com.example.turismochocanoapp

data class ReservaItem(
    val nombre: String,
    val pnr: String,
    val dni: String,
    val ruta: String,
    val asiento: String,
    val estado: String,
    val colorEstado: String
)

object ReservaRepository {
    val listaReservas = mutableListOf(
        ReservaItem("Ximena", "TC-001", "74859612", "Uchiza ➔ Lima", "Asiento N° 3", "PENDIENTE", "#E67E22"),
        ReservaItem("Mafalda", "TC-002", "45896321", "Lima ➔ Uchiza", "Asiento N° 5", "EN PROCESO", "#27AE60"),
        ReservaItem("Carlos", "TC-003", "12365478", "Uchiza ➔ Lima", "Asiento N° 8", "CONFIRMADO", "#27AE60")
    )
}