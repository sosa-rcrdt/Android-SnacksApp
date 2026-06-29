package com.empresa.snacks.dominio.modelo

data class ResumenCarrito(
    val detalles: List<DetalleVenta>
) {
    val cantidadTotalProductos: Int
        get() = detalles.sumOf { detalle -> detalle.cantidad }

    val totalCentavos: Long
        get() = detalles.sumOf { detalle -> detalle.subtotalCentavos }

    val estaVacio: Boolean
        get() = detalles.isEmpty()
}