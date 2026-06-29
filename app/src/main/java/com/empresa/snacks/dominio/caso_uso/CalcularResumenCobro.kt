package com.empresa.snacks.dominio.caso_uso

import com.empresa.snacks.dominio.modelo.ResumenCarrito
import com.empresa.snacks.dominio.modelo.ResumenCobro

object CalcularResumenCobro {

    fun ejecutar(
        resumenCarrito: ResumenCarrito,
        dineroRecibidoCentavos: Long?
    ): ResumenCobro {
        return ResumenCobro(
            totalCentavos = resumenCarrito.totalCentavos,
            dineroRecibidoCentavos = dineroRecibidoCentavos
        )
    }
}