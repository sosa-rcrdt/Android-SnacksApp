package com.empresa.snacks.dominio.modelo

enum class CategoriaProducto(
    val nombreVisible: String
) {
    ELOTE("Elotes"),
    ESQUITE("Esquites"),
    SNACK("Snacks"),
    BEBIDA("Bebidas"),
    POSTRE("Postres"),
    ESPECIAL("Especiales")
}