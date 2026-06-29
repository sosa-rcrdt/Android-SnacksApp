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
import com.empresa.snacks.comun.formato.formatearCentavosComoPesos
import com.empresa.snacks.datos.local.CatalogoProductosLocal
import com.empresa.snacks.dominio.caso_uso.CalcularResumenCarrito
import com.empresa.snacks.dominio.modelo.Producto
import com.empresa.snacks.dominio.modelo.ResumenCarrito
import com.empresa.snacks.ui.theme.SnacksAppTheme

@Composable
fun PantallaCalcularCompra(
    modificador: Modifier = Modifier,
    alRegresar: () -> Unit = {},
    productos: List<Producto> = CatalogoProductosLocal.obtenerProductosActivos(),
    cantidadesPorProducto: Map<Int, Int> = emptyMap(),
    alAumentarCantidad: (Producto) -> Unit = {},
    alDisminuirCantidad: (Producto) -> Unit = {},
    alVerResumenCompra: () -> Unit = {}
) {
    val resumenCarrito = CalcularResumenCarrito.ejecutar(
        productos = productos,
        cantidadesPorProducto = cantidadesPorProducto
    )

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

        SeccionCatalogoProductos(
            productos = productos,
            cantidadesPorProducto = cantidadesPorProducto,
            alAumentarCantidad = alAumentarCantidad,
            alDisminuirCantidad = alDisminuirCantidad
        )

        Spacer(modifier = Modifier.height(16.dp))

        ResumenParcialCompra(
            resumenCarrito = resumenCarrito
        )

        Spacer(modifier = Modifier.height(16.dp))

        BotonVerResumenCompra(
            resumenCarrito = resumenCarrito,
            alVerResumenCompra = alVerResumenCompra
        )
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
        text = "Seleccionar productos",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        color = ColorVerdeOscuro
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        text = "Agrega al carrito los productos que se venderán.",
        style = MaterialTheme.typography.bodyMedium,
        color = ColorVerdePrincipal
    )
}

@Composable
private fun SeccionCatalogoProductos(
    productos: List<Producto>,
    cantidadesPorProducto: Map<Int, Int>,
    alAumentarCantidad: (Producto) -> Unit,
    alDisminuirCantidad: (Producto) -> Unit
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
                text = "Productos",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = ColorVerdeOscuro
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Catálogo disponible para registrar una compra.",
                style = MaterialTheme.typography.bodySmall,
                color = ColorVerdePrincipal
            )

            Spacer(modifier = Modifier.height(16.dp))

            productos.forEach { producto ->
                ProductoCompra(
                    producto = producto,
                    cantidad = cantidadesPorProducto[producto.id] ?: 0,
                    alAumentarCantidad = {
                        alAumentarCantidad(producto)
                    },
                    alDisminuirCantidad = {
                        alDisminuirCantidad(producto)
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
private fun ProductoCompra(
    producto: Producto,
    cantidad: Int,
    alAumentarCantidad: () -> Unit,
    alDisminuirCantidad: () -> Unit
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
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImagenProductoGenerica()

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = producto.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = ColorVerdeOscuro
                )

                Text(
                    text = producto.categoria.nombreVisible,
                    style = MaterialTheme.typography.bodySmall,
                    color = ColorVerdePrincipal
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = formatearCentavosComoPesos(producto.precioCentavos),
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
                    habilitado = cantidad > 0,
                    alPresionar = alDisminuirCantidad
                )

                Text(
                    text = cantidad.toString(),
                    modifier = Modifier.padding(horizontal = 10.dp),
                    fontWeight = FontWeight.Bold,
                    color = ColorVerdeOscuro
                )

                BotonCantidad(
                    texto = "+",
                    habilitado = true,
                    alPresionar = alAumentarCantidad
                )
            }
        }
    }
}

@Composable
private fun ImagenProductoGenerica() {
    Card(
        modifier = Modifier.size(58.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = ColorFondoAplicacion
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🌽",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Composable
private fun BotonCantidad(
    texto: String,
    habilitado: Boolean,
    alPresionar: () -> Unit
) {
    Button(
        onClick = alPresionar,
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
private fun ResumenParcialCompra(
    resumenCarrito: ResumenCarrito
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Carrito actual",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = ColorVerdeOscuro
            )

            Spacer(modifier = Modifier.height(10.dp))

            FilaResumenParcial(
                etiqueta = "Productos agregados",
                valor = resumenCarrito.cantidadTotalProductos.toString()
            )

            Spacer(modifier = Modifier.height(6.dp))

            FilaResumenParcial(
                etiqueta = "Total parcial",
                valor = formatearCentavosComoPesos(resumenCarrito.totalCentavos)
            )
        }
    }
}

@Composable
private fun FilaResumenParcial(
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

@Composable
private fun BotonVerResumenCompra(
    resumenCarrito: ResumenCarrito,
    alVerResumenCompra: () -> Unit
) {
    val textoBoton = if (!resumenCarrito.estaVacio) {
        "Ver resumen de compra (${resumenCarrito.cantidadTotalProductos})"
    } else {
        "Ver resumen de compra"
    }

    Button(
        onClick = alVerResumenCompra,
        enabled = !resumenCarrito.estaVacio,
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
            text = textoBoton,
            fontWeight = FontWeight.Bold
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