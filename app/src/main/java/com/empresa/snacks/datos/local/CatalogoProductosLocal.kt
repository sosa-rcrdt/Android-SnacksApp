package com.empresa.snacks.datos.local

import com.empresa.snacks.dominio.modelo.CategoriaProducto
import com.empresa.snacks.dominio.modelo.Producto

object CatalogoProductosLocal {

    fun obtenerProductosActivos(): List<Producto> {
        return productos.filter { producto -> producto.estaActivo }
    }

    private val productos = listOf(
        Producto(
            id = 1,
            nombre = "Elote",
            precioCentavos = 3000,
            categoria = CategoriaProducto.ELOTE
        ),
        Producto(
            id = 2,
            nombre = "Esquite chico",
            precioCentavos = 3000,
            categoria = CategoriaProducto.ESQUITE
        ),
        Producto(
            id = 3,
            nombre = "Esquite mediano",
            precioCentavos = 3500,
            categoria = CategoriaProducto.ESQUITE
        ),
        Producto(
            id = 4,
            nombre = "Esquite grande",
            precioCentavos = 4500,
            categoria = CategoriaProducto.ESQUITE
        ),
        Producto(
            id = 5,
            nombre = "Doriesquite",
            precioCentavos = 6000,
            categoria = CategoriaProducto.SNACK
        ),
        Producto(
            id = 6,
            nombre = "Sopa con esquite",
            precioCentavos = 6000,
            categoria = CategoriaProducto.SNACK
        ),
        Producto(
            id = 7,
            nombre = "Nachos con esquite",
            precioCentavos = 6000,
            categoria = CategoriaProducto.SNACK
        ),
        Producto(
            id = 8,
            nombre = "Elote revolcado",
            precioCentavos = 5000,
            categoria = CategoriaProducto.ELOTE
        ),
        Producto(
            id = 9,
            nombre = "Cheetoelote",
            precioCentavos = 5000,
            categoria = CategoriaProducto.ELOTE
        ),
        Producto(
            id = 10,
            nombre = "Dorilocos",
            precioCentavos = 6000,
            categoria = CategoriaProducto.SNACK
        ),
        Producto(
            id = 11,
            nombre = "Nachos con queso y jalapeños",
            precioCentavos = 6000,
            categoria = CategoriaProducto.SNACK
        ),
        Producto(
            id = 12,
            nombre = "Nachote",
            precioCentavos = 5000,
            categoria = CategoriaProducto.SNACK
        ),
        Producto(
            id = 13,
            nombre = "Frappes",
            precioCentavos = 5000,
            categoria = CategoriaProducto.BEBIDA
        ),
        Producto(
            id = 14,
            nombre = "Gomi Boing",
            precioCentavos = 5000,
            categoria = CategoriaProducto.BEBIDA
        ),
        Producto(
            id = 15,
            nombre = "Arizona preparado",
            precioCentavos = 4000,
            categoria = CategoriaProducto.BEBIDA
        ),
        Producto(
            id = 16,
            nombre = "Waffles",
            precioCentavos = 4000,
            categoria = CategoriaProducto.POSTRE
        ),
        Producto(
            id = 17,
            nombre = "Mini hot cakes",
            precioCentavos = 4000,
            categoria = CategoriaProducto.POSTRE
        ),
        Producto(
            id = 18,
            nombre = "Cheevaso",
            precioCentavos = 5000,
            categoria = CategoriaProducto.ESPECIAL
        ),
        Producto(
            id = 19,
            nombre = "La Greñuda",
            precioCentavos = 10000,
            categoria = CategoriaProducto.ESPECIAL
        ),
        Producto(
            id = 20,
            nombre = "Charolita mix",
            precioCentavos = 14000,
            categoria = CategoriaProducto.ESPECIAL
        )
    )
}