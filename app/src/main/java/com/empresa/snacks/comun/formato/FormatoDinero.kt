package com.empresa.snacks.comun.formato

fun formatearCentavosComoPesos(centavos: Long): String {
    val pesos = centavos / 100
    val centavosRestantes = centavos % 100

    return "$$pesos.${centavosRestantes.toString().padStart(2, '0')}"
}

fun convertirTextoPesosACentavos(texto: String): Long? {
    val textoLimpio = texto
        .trim()
        .replace("$", "")
        .replace(" ", "")
        .replace(",", ".")

    if (textoLimpio.isBlank()) {
        return 0L
    }

    val formatoValido = Regex("^\\d*(\\.\\d{0,2})?$")

    if (!formatoValido.matches(textoLimpio)) {
        return null
    }

    val partes = textoLimpio.split(".")
    val pesosTexto = partes.getOrNull(0).orEmpty().ifBlank { "0" }
    val centavosTexto = partes.getOrNull(1).orEmpty()

    val pesos = pesosTexto.toLongOrNull() ?: return null
    val centavos = centavosTexto
        .padEnd(2, '0')
        .ifBlank { "00" }
        .toLongOrNull() ?: return null

    return (pesos * 100) + centavos
}