package com.empresa.snacks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.empresa.snacks.comun.estilo.ColorFondoAplicacion
import com.empresa.snacks.presentacion.compra.PantallaCalcularCompra
import com.empresa.snacks.presentacion.menu.PantallaMenuPrincipal
import com.empresa.snacks.ui.theme.SnacksAppTheme

private enum class PantallaActual {
    MENU_PRINCIPAL,
    CALCULAR_COMPRA
}

class MainActivity : ComponentActivity() {

    // onCreate se ejecuta cuando Android crea la pantalla principal de la app.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Permite usar mejor el espacio disponible de la pantalla.
        enableEdgeToEdge()

        // setContent conecta Android con la interfaz hecha en Jetpack Compose.
        setContent {
            var pantallaActual by remember {
                mutableStateOf(PantallaActual.MENU_PRINCIPAL)
            }

            SnacksAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = ColorFondoAplicacion
                ) { espacioSeguroPantalla ->

                    when (pantallaActual) {
                        PantallaActual.MENU_PRINCIPAL -> {
                            PantallaMenuPrincipal(
                                modificador = Modifier.padding(espacioSeguroPantalla),
                                alPresionarCalcularCompra = {
                                    pantallaActual = PantallaActual.CALCULAR_COMPRA
                                }
                            )
                        }

                        PantallaActual.CALCULAR_COMPRA -> {
                            PantallaCalcularCompra(
                                modificador = Modifier.padding(espacioSeguroPantalla),
                                alRegresar = {
                                    pantallaActual = PantallaActual.MENU_PRINCIPAL
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}