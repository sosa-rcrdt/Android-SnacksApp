package com.empresa.snacks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.empresa.snacks.comun.estilo.ColorFondoAplicacion
import com.empresa.snacks.datos.local.CatalogoProductosLocal
import com.empresa.snacks.dominio.caso_uso.CalcularResumenCarrito
import com.empresa.snacks.presentacion.compra.PantallaCalcularCompra
import com.empresa.snacks.presentacion.compra.PantallaResumenCompra
import com.empresa.snacks.presentacion.menu.PantallaMenuPrincipal
import com.empresa.snacks.ui.theme.SnacksAppTheme

private enum class PantallaActual {
    MENU_PRINCIPAL,
    SELECCIONAR_PRODUCTOS,
    RESUMEN_COMPRA
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

            val productos = remember {
                CatalogoProductosLocal.obtenerProductosActivos()
            }

            val cantidadesPorProducto = remember {
                mutableStateMapOf<Int, Int>()
            }

            val resumenCarrito = CalcularResumenCarrito.ejecutar(
                productos = productos,
                cantidadesPorProducto = cantidadesPorProducto
            )

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
                                    pantallaActual = PantallaActual.SELECCIONAR_PRODUCTOS
                                }
                            )
                        }

                        PantallaActual.SELECCIONAR_PRODUCTOS -> {
                            PantallaCalcularCompra(
                                modificador = Modifier.padding(espacioSeguroPantalla),
                                productos = productos,
                                cantidadesPorProducto = cantidadesPorProducto,
                                alRegresar = {
                                    pantallaActual = PantallaActual.MENU_PRINCIPAL
                                },
                                alAumentarCantidad = { producto ->
                                    val cantidadActual = cantidadesPorProducto[producto.id] ?: 0
                                    cantidadesPorProducto[producto.id] = cantidadActual + 1
                                },
                                alDisminuirCantidad = { producto ->
                                    val cantidadActual = cantidadesPorProducto[producto.id] ?: 0

                                    if (cantidadActual > 1) {
                                        cantidadesPorProducto[producto.id] = cantidadActual - 1
                                    } else {
                                        cantidadesPorProducto.remove(producto.id)
                                    }
                                },
                                alVerResumenCompra = {
                                    if (!resumenCarrito.estaVacio) {
                                        pantallaActual = PantallaActual.RESUMEN_COMPRA
                                    }
                                }
                            )
                        }

                        PantallaActual.RESUMEN_COMPRA -> {
                            PantallaResumenCompra(
                                modificador = Modifier.padding(espacioSeguroPantalla),
                                resumenCarrito = resumenCarrito,
                                alRegresarProductos = {
                                    pantallaActual = PantallaActual.SELECCIONAR_PRODUCTOS
                                },
                                alConfirmarVenta = {
                                    // Después guardaremos la venta y limpiaremos el carrito.
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}