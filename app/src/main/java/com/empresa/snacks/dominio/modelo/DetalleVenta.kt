package com.empresa.snacks.dominio.modelo

data class DetalleVenta(
    val productoId: Int,
    val nombreProductoSnapshot: String,
    val precioUnitarioCentavosSnapshot: Long,
    val cantidad: Int
) {
    val subtotalCentavos: Long
        get() = precioUnitarioCentavosSnapshot * cantidad

    init {
        require(productoId > 0) { "El id del producto debe ser mayor que 0." }
        require(nombreProductoSnapshot.isNotBlank()) { "El nombre del producto no puede estar vacío." }
        require(precioUnitarioCentavosSnapshot >= 0) { "El precio unitario no puede ser negativo." }
        require(cantidad > 0) { "La cantidad debe ser mayor que 0." }
    }

    companion object {
        fun desdeProducto(
            producto: Producto,
            cantidad: Int
        ): DetalleVenta {
            return DetalleVenta(
                productoId = producto.id,
                nombreProductoSnapshot = producto.nombre,
                precioUnitarioCentavosSnapshot = producto.precioCentavos,
                cantidad = cantidad
            )
        }
    }
}