package com.empresa.snacks.comun.formato

fun formatearCentavosComoPesos(centavos: Long): String {
    val pesos = centavos / 100
    val centavosRestantes = centavos % 100

    return "$$pesos.${centavosRestantes.toString().padStart(2, '0')}"
}