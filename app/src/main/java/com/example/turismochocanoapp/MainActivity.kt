package com.example.turismochocanoapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            navegarA(InicioFragment(), "Inicio", mostrarBarraYMenu = false)
            navView.setCheckedItem(R.id.nav_inicio)
        }

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_inicio -> navegarA(InicioFragment(), "Inicio", mostrarBarraYMenu = false)
                R.id.nav_nosotros -> navegarA(NosotrosFragment(), "Nosotros", mostrarBarraYMenu = true)
                R.id.nav_contactos -> navegarA(ContactosFragment(), "Contactos", mostrarBarraYMenu = true)
                R.id.nav_login -> navegarA(LoginFragment(), "Login", mostrarBarraYMenu = false)
                R.id.nav_dashboard -> navegarA(DashboardFragment(), "Dashboard Operativo", mostrarBarraYMenu = true)
                R.id.nav_salarios -> navegarA(SalariosFragment(), "Consulta de Salarios", mostrarBarraYMenu = true)
                R.id.nav_reserva -> navegarA(ReservaFragment(), "Nueva Reserva", mostrarBarraYMenu = true)
                R.id.nav_logout -> {
                    Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
                    navegarA(InicioFragment(), "Inicio", mostrarBarraYMenu = false)
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }
    fun navegarA(fragment: androidx.fragment.app.Fragment, titulo: String, mostrarBarraYMenu: Boolean) {
        supportActionBar?.title = titulo

        if (mostrarBarraYMenu) {
            toolbar.visibility = View.VISIBLE
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        } else {
            toolbar.visibility = View.GONE
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun iniciarSesionExitoso() {
        navegarA(DashboardFragment(), "Dashboard Operativo", mostrarBarraYMenu = true)
    }
}