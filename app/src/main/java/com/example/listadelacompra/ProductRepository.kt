package com.example.listadelacompra.repository

import com.example.listadelacompra.data.Producto
import com.example.listadelacompra.data.ProductoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductoRepository(private val productoDao: ProductoDao) {
    suspend fun obtenerProductos(): List<Producto> = withContext(Dispatchers.IO) {
        productoDao.obtenerProductos()
    }

    suspend fun insertarProducto(producto: Producto) = withContext(Dispatchers.IO) {
        productoDao.insertarProducto(producto)
    }

    suspend fun eliminarProducto(producto: Producto) = withContext(Dispatchers.IO) {
        productoDao.eliminarProducto(producto)
    }
}
