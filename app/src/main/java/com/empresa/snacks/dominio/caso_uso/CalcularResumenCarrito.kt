package com.empresa.snacks.dominio.caso_uso

import com.empresa.snacks.dominio.modelo.DetalleVenta
import com.empresa.snacks.dominio.modelo.Producto
import com.empresa.snacks.dominio.modelo.ResumenCarrito

object CalcularResumenCarrito {

    fun ejecutar(
        productos: List<Producto>,
        cantidadesPorProducto: Map<Int, Int>
    ): ResumenCarrito {
        val detalles = productos.mapNotNull { producto ->
            val cantidad = cantidadesPorProducto[producto.id] ?: 0

            if (cantidad > 0) {
                DetalleVenta.desdeProducto(
                    producto = producto,
                    cantidad = cantidad
                )
            } else {
                null
            }
        }

        return ResumenCarrito(
            detalles = detalles
        )
    }
}