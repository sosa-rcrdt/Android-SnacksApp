package com.empresa.snacks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.empresa.snacks.comun.estilo.ColorFondoAplicacion
import com.empresa.snacks.presentacion.menu.PantallaMenuPrincipal
import com.empresa.snacks.ui.theme.SnacksAppTheme

class MainActivity : ComponentActivity() {

    // onCreate se ejecuta cuando Android crea la pantalla principal de la app.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Permite usar mejor el espacio disponible de la pantalla.
        enableEdgeToEdge()

        // setContent conecta Android con la interfaz hecha en Jetpack Compose.
        setContent {
            SnacksAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = ColorFondoAplicacion
                ) { espacioSeguroPantalla ->

                    PantallaMenuPrincipal(
                        modificador = Modifier.padding(espacioSeguroPantalla)
                    )
                }
            }
        }
    }
}