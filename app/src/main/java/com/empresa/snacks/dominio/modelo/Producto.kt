package com.empresa.snacks.dominio.modelo

data class Producto(
    val id: Int,
    val nombre: String,
    val precioCentavos: Long,
    val categoria: CategoriaProducto,
    val estaActivo: Boolean = true
) {
    init {
        require(id > 0) { "El id del producto debe ser mayor que 0." }
        require(nombre.isNotBlank()) { "El nombre del producto no puede estar vacío." }
        require(precioCentavos >= 0) { "El precio del producto no puede ser negativo." }
    }
}