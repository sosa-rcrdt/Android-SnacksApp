package com.empresa.snacks.dominio.modelo

data class ResumenCobro(
    val totalCentavos: Long,
    val dineroRecibidoCentavos: Long?
) {
    val dineroRecibidoValido: Boolean
        get() = dineroRecibidoCentavos != null

    val pagoSuficiente: Boolean
        get() = dineroRecibidoCentavos != null &&
                dineroRecibidoCentavos >= totalCentavos &&
                totalCentavos > 0

    val cambioCentavos: Long
        get() {
            val recibido = dineroRecibidoCentavos ?: 0L
            val cambio = recibido - totalCentavos

            return if (cambio > 0) cambio else 0L
        }

    val faltanteCentavos: Long
        get() {
            val recibido = dineroRecibidoCentavos ?: 0L
            val faltante = totalCentavos - recibido

            return if (faltante > 0) faltante else 0L
        }
}