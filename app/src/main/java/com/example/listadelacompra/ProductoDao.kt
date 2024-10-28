package com.example.listadelacompra.data

import androidx.room.*

@Dao
interface ProductoDao {
    @Query("SELECT * FROM productos")
    fun obtenerProductos(): List<Producto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarProducto(producto: Producto)

    @Delete
    fun eliminarProducto(producto: Producto)
}
