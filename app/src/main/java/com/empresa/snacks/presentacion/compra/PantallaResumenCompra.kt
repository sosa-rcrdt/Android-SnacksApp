package com.empresa.snacks.presentacion.compra

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.empresa.snacks.comun.estilo.ColorAmarilloMaiz
import com.empresa.snacks.comun.estilo.ColorBotonDeshabilitado
import com.empresa.snacks.comun.estilo.ColorFondoAplicacion
import com.empresa.snacks.comun.estilo.ColorTarjetaMenu
import com.empresa.snacks.comun.estilo.ColorTextoDeshabilitado
import com.empresa.snacks.comun.estilo.ColorVerdeOscuro
import com.empresa.snacks.comun.estilo.ColorVerdePrincipal
import com.empresa.snacks.comun.formato.convertirTextoPesosACentavos
import com.empresa.snacks.comun.formato.formatearCentavosComoPesos
import com.empresa.snacks.dominio.caso_uso.CalcularResumenCobro
import com.empresa.snacks.dominio.modelo.CategoriaProducto
import com.empresa.snacks.dominio.modelo.DetalleVenta
import com.empresa.snacks.dominio.modelo.Producto
import com.empresa.snacks.dominio.modelo.ResumenCarrito
import com.empresa.snacks.dominio.modelo.ResumenCobro
import com.empresa.snacks.ui.theme.SnacksAppTheme

@Composable
fun PantallaResumenCompra(
    modificador: Modifier = Modifier,
    resumenCarrito: ResumenCarrito,
    alRegresarProductos: () -> Unit = {},
    alConfirmarVenta: () -> Unit = {}
) {
    var textoDineroRecibido by rememberSaveable {
        mutableStateOf("")
    }

    val dineroRecibidoCentavos = convertirTextoPesosACentavos(textoDineroRecibido)

    val resumenCobro = CalcularResumenCobro.ejecutar(
        resumenCarrito = resumenCarrito,
        dineroRecibidoCentavos = dineroRecibidoCentavos
    )

    Column(
        modifier = modificador
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        EncabezadoResumenCompra(
            alRegresarProductos = alRegresarProductos
        )

        Spacer(modifier = Modifier.height(16.dp))

        SeccionDetalleResumen(
            resumenCarrito = resumenCarrito
        )

        Spacer(modifier = Modifier.height(16.dp))

        SeccionCobro(
            textoDineroRecibido = textoDineroRecibido,
            alCambiarDineroRecibido = { nuevoValor ->
                textoDineroRecibido = nuevoValor
            },
            resumenCobro = resumenCobro
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = alConfirmarVenta,
            enabled = resumenCobro.pagoSuficiente,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorAmarilloMaiz,
                contentColor = ColorVerdeOscuro,
                disabledContainerColor = ColorBotonDeshabilitado,
                disabledContentColor = ColorTextoDeshabilitado
            )
        ) {
            Text(
                text = "Confirmar venta",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun EncabezadoResumenCompra(
    alRegresarProductos: () -> Unit
) {
    Button(
        onClick = alRegresarProductos,
        shape = RoundedCornerShape(50.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = ColorVerdeOscuro,
            contentColor = ColorAmarilloMaiz
        )
    ) {
        Text(
            text = "← Productos",
            fontWeight = FontWeight.Bold
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Resumen de compra",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        color = ColorVerdeOscuro
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        text = "Revisa los productos seleccionados y calcula el cambio.",
        style = MaterialTheme.typography.bodyMedium,
        color = ColorVerdePrincipal
    )
}

@Composable
private fun SeccionDetalleResumen(
    resumenCarrito: ResumenCarrito
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = ColorTarjetaMenu
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Productos seleccionados",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = ColorVerdeOscuro
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (resumenCarrito.estaVacio) {
                Text(
                    text = "No hay productos seleccionados.",
                    color = ColorVerdePrincipal
                )
            } else {
                resumenCarrito.detalles.forEach { detalle ->
                    FilaDetalleProducto(
                        detalle = detalle
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.height(8.dp))

                FilaResumenCobro(
                    etiqueta = "Productos",
                    valor = resumenCarrito.cantidadTotalProductos.toString()
                )

                Spacer(modifier = Modifier.height(6.dp))

                FilaResumenCobro(
                    etiqueta = "Total",
                    valor = formatearCentavosComoPesos(resumenCarrito.totalCentavos),
                    resaltar = true
                )
            }
        }
    }
}

@Composable
private fun FilaDetalleProducto(
    detalle: DetalleVenta
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = detalle.nombreProductoSnapshot,
                    fontWeight = FontWeight.Bold,
                    color = ColorVerdeOscuro
                )

                Text(
                    text = "${detalle.cantidad} x ${
                        formatearCentavosComoPesos(detalle.precioUnitarioCentavosSnapshot)
                    }",
                    style = MaterialTheme.typography.bodySmall,
                    color = ColorVerdePrincipal
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = formatearCentavosComoPesos(detalle.subtotalCentavos),
                fontWeight = FontWeight.Bold,
                color = ColorVerdeOscuro
            )
        }
    }
}

@Composable
private fun SeccionCobro(
    textoDineroRecibido: String,
    alCambiarDineroRecibido: (String) -> Unit,
    resumenCobro: ResumenCobro
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Cobro",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = ColorVerdeOscuro
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = textoDineroRecibido,
                onValueChange = alCambiarDineroRecibido,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Dinero recibido")
                },
                placeholder = {
                    Text(text = "Ej. 200")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                singleLine = true,
                isError = textoDineroRecibido.isNotBlank() && !resumenCobro.dineroRecibidoValido
            )

            Spacer(modifier = Modifier.height(12.dp))

            MensajeCobro(
                textoDineroRecibido = textoDineroRecibido,
                resumenCobro = resumenCobro
            )

            Spacer(modifier = Modifier.height(16.dp))

            FilaResumenCobro(
                etiqueta = "Total a pagar",
                valor = formatearCentavosComoPesos(resumenCobro.totalCentavos)
            )

            Spacer(modifier = Modifier.height(8.dp))

            FilaResumenCobro(
                etiqueta = "Cambio a entregar",
                valor = formatearCentavosComoPesos(resumenCobro.cambioCentavos),
                resaltar = true
            )
        }
    }
}

@Composable
private fun MensajeCobro(
    textoDineroRecibido: String,
    resumenCobro: ResumenCobro
) {
    val mensaje = when {
        textoDineroRecibido.isBlank() -> "Ingresa la cantidad recibida para calcular el cambio."

        !resumenCobro.dineroRecibidoValido -> "Ingresa una cantidad válida."

        resumenCobro.pagoSuficiente -> "Pago suficiente. Puedes confirmar la venta."

        else -> "Faltan ${
            formatearCentavosComoPesos(resumenCobro.faltanteCentavos)
        } para completar el pago."
    }

    Text(
        text = mensaje,
        style = MaterialTheme.typography.bodySmall,
        color = if (resumenCobro.pagoSuficiente) {
            ColorVerdePrincipal
        } else {
            ColorVerdeOscuro
        }
    )
}

@Composable
private fun FilaResumenCobro(
    etiqueta: String,
    valor: String,
    resaltar: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = etiqueta,
            color = ColorVerdePrincipal
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = valor,
            fontWeight = if (resaltar) FontWeight.Bold else FontWeight.SemiBold,
            color = ColorVerdeOscuro
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFBF3)
@Composable
fun VistaPreviaPantallaResumenCompra() {
    val productoEjemplo = Producto(
        id = 1,
        nombre = "Elote",
        precioCentavos = 3000,
        categoria = CategoriaProducto.ELOTE
    )

    val resumenCarrito = ResumenCarrito(
        detalles = listOf(
            DetalleVenta.desdeProducto(
                producto = productoEjemplo,
                cantidad = 2
            )
        )
    )

    SnacksAppTheme {
        PantallaResumenCompra(
            resumenCarrito = resumenCarrito
        )
    }
}