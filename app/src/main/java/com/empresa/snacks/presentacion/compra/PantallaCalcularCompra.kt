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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.empresa.snacks.comun.estilo.ColorAmarilloMaiz
import com.empresa.snacks.comun.estilo.ColorBotonDeshabilitado
import com.empresa.snacks.comun.estilo.ColorFondoAplicacion
import com.empresa.snacks.comun.estilo.ColorTarjetaMenu
import com.empresa.snacks.comun.estilo.ColorTextoDeshabilitado
import com.empresa.snacks.comun.estilo.ColorVerdeOscuro
import com.empresa.snacks.comun.estilo.ColorVerdePrincipal
import com.empresa.snacks.ui.theme.SnacksAppTheme

@Composable
fun PantallaCalcularCompra(
    modificador: Modifier = Modifier,
    alRegresar: () -> Unit = {}
) {
    Column(
        modifier = modificador
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        EncabezadoCompra(
            alRegresar = alRegresar
        )

        Spacer(modifier = Modifier.height(16.dp))

        SeccionCatalogoProductos()

        Spacer(modifier = Modifier.height(16.dp))

        SeccionResumenCompra()
    }
}

@Composable
private fun EncabezadoCompra(
    alRegresar: () -> Unit
) {
    Button(
        onClick = alRegresar,
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
            text = "← Menú principal",
            fontWeight = FontWeight.Bold
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Calcular compra",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        color = ColorVerdeOscuro
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        text = "Selecciona los productos vendidos y calcula el cambio.",
        style = MaterialTheme.typography.bodyMedium,
        color = ColorVerdePrincipal
    )
}

@Composable
private fun SeccionCatalogoProductos() {
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
                text = "Productos",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = ColorVerdeOscuro
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Por ahora esta sección solo muestra el diseño. En el siguiente paso conectaremos el catálogo real.",
                style = MaterialTheme.typography.bodySmall,
                color = ColorVerdePrincipal
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProductoCompraEjemplo(
                nombre = "Elote",
                categoria = "Elotes",
                precio = "$0.00"
            )

            Spacer(modifier = Modifier.height(10.dp))

            ProductoCompraEjemplo(
                nombre = "Esquite",
                categoria = "Esquites",
                precio = "$0.00"
            )

            Spacer(modifier = Modifier.height(10.dp))

            ProductoCompraEjemplo(
                nombre = "Dorilocos",
                categoria = "Snacks",
                precio = "$0.00"
            )
        }
    }
}

@Composable
private fun ProductoCompraEjemplo(
    nombre: String,
    categoria: String,
    precio: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = ColorVerdeOscuro
                )

                Text(
                    text = categoria,
                    style = MaterialTheme.typography.bodySmall,
                    color = ColorVerdePrincipal
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = precio,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = ColorVerdeOscuro
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                BotonCantidad(
                    texto = "-",
                    habilitado = false
                )

                Text(
                    text = "0",
                    modifier = Modifier.padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    color = ColorVerdeOscuro
                )

                BotonCantidad(
                    texto = "+",
                    habilitado = false
                )
            }
        }
    }
}

@Composable
private fun BotonCantidad(
    texto: String,
    habilitado: Boolean
) {
    Button(
        onClick = {
            // Después aquí aumentaremos o reduciremos la cantidad del producto.
        },
        enabled = habilitado,
        modifier = Modifier.size(36.dp),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ColorAmarilloMaiz,
            contentColor = ColorVerdeOscuro,
            disabledContainerColor = ColorBotonDeshabilitado,
            disabledContentColor = ColorTextoDeshabilitado
        )
    ) {
        Text(
            text = texto,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun SeccionResumenCompra() {
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
                text = "Resumen de compra",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = ColorVerdeOscuro
            )

            Spacer(modifier = Modifier.height(16.dp))

            FilaResumen(
                etiqueta = "Productos seleccionados",
                valor = "0"
            )

            Spacer(modifier = Modifier.height(8.dp))

            FilaResumen(
                etiqueta = "Total",
                valor = "$0.00"
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {
                    // Después aquí guardaremos el dinero recibido.
                },
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Dinero recibido")
                },
                placeholder = {
                    Text(text = "$0.00")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            FilaResumen(
                etiqueta = "Cambio a entregar",
                valor = "$0.00"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Después aquí registraremos la venta.
                },
                enabled = false,
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
}

@Composable
private fun FilaResumen(
    etiqueta: String,
    valor: String
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
            fontWeight = FontWeight.Bold,
            color = ColorVerdeOscuro
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFBF3)
@Composable
fun VistaPreviaPantallaCalcularCompra() {
    SnacksAppTheme {
        PantallaCalcularCompra()
    }
}