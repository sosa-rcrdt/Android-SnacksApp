package com.empresa.snacks.dominio.modelo

data class Venta(
    val id: Long = 0,
    val fechaHoraCreacionMillis: Long,
    val detalles: List<DetalleVenta>,
    val dineroRecibidoCentavos: Long,
    val estado: EstadoVenta = EstadoVenta.COMPLETADA
) {
    val totalCentavos: Long
        get() = detalles.sumOf { detalle -> detalle.subtotalCentavos }

    val cambioCentavos: Long
        get() = dineroRecibidoCentavos - totalCentavos

    val pagoSuficiente: Boolean
        get() = dineroRecibidoCentavos >= totalCentavos

    val cantidadTotalProductos: Int
        get() = detalles.sumOf { detalle -> detalle.cantidad }

    init {
        require(fechaHoraCreacionMillis > 0) { "La venta debe tener una fecha válida." }
        require(detalles.isNotEmpty()) { "La venta debe tener al menos un producto." }
        require(dineroRecibidoCentavos >= 0) { "El dinero recibido no puede ser negativo." }
    }
}