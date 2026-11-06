#  Turismo Chocano - App Móvil de Gestión de Reservas

![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-34A853?style=for-the-badge&logo=android&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)

**Turismo Chocano** es una aplicación móvil nativa desarrollada para Android orientada a la gestión operativa de venta de pasajes, control de reservas en tiempo real y cálculo de comisiones/salarios para personal administrativo.

---

##  Tecnologías y Herramientas Utilizadas

* **Lenguaje de Programación:** ![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=kotlin&logoColor=white) **Kotlin** (Nativo)
* **IDE:** ![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84?style=flat-square&logo=android-studio&logoColor=white) **Android Studio**
* **Interfaz de Usuario (UI):** XML Layouts con diseño personalizado en Rojo Corporativo (`#C8102E`) y crema.
* **Persistencia en Memoria:** Patrón Singleton (`ReservaRepository`) para sincronización dinámica de listas entre fragments.
* **Control de Versiones:** ![Git](https://img.shields.io/badge/Git-F05032?style=flat-square&logo=git&logoColor=white) **Git** & ![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white) **GitHub**

---

## Características Principales

- **Acceso Restringido:** Pantalla de Inicio pública y módulo de Autenticación de Administrador.
- **Dashboard Operativo:** Panel dinámico con tarjetas color-coded según el estado de la reserva (Pendiente, En Proceso, Confirmado) y buscador en tiempo real por pasajero, PNR o DNI.
- **Gestión de Reservas:** Formulario interactivo con validaciones de DNI de 8 dígitos, asignación de ruta (Uchiza ➔ Lima), selector de fecha (hasta 2026) y asignación de asiento.
- **Acciones CRUD:** Visualización, edición rápida y eliminación de registros directamente desde la lista principal.
- **Módulos Independientes:** Módulo de cálculo de salarios/comisiones y sección institucional "Sobre Nosotros" y "Contactos".

---

##  Cómo Ejecutar la Aplicación

### Requisitos Previos
* Android Studio (versión Jellyfish o superior recomendada).
* JDK 17 o superior.
* Dispositivo móvil Android (Android 8.0 / API 26 en adelante) o un Emulador AVD.

### Pasos para Ejecutar

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/VALDIVIEZOatero/TurismoChocanoApppp.git](https://github.com/VALDIVIEZOatero/TurismoChocanoApppp.git)
### Generar el APK Instalable
Para compilar y obtener el archivo ejecutable .apk:

Bash
.\gradlew assembleDebug
El archivo generado se ubicará en: app/build/outputs/apk/debug/app-debug.apk


### Integrantes del Proyecto

Zurita Rimaicuna, Abner

Valdiviezo Atero, Yoli Jhunior
