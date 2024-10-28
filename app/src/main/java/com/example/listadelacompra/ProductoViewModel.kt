package com.example.listadelacompra.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listadelacompra.data.Producto
import com.example.listadelacompra.repository.ProductoRepository
import kotlinx.coroutines.launch

class ProductoViewModel(private val repository: ProductoRepository) : ViewModel() {
    private val _productos = MutableLiveData<List<Producto>>()
    val productos: LiveData<List<Producto>> = _productos

    init {
        cargarProductos()
    }

    private fun cargarProductos() {
        viewModelScope.launch {
            _productos.value = repository.obtenerProductos()
        }
    }

    fun agregarProducto(producto: Producto) {
        viewModelScope.launch {
            repository.insertarProducto(producto)
            cargarProductos()
        }
    }

    fun eliminarProducto(producto: Producto) {
        viewModelScope.launch {
            repository.eliminarProducto(producto)
            cargarProductos()
        }
    }


    fun calcularTotal(): Double {
        return productos.value?.sumOf { it.precio ?: 0.0 } ?: 0.0
    }

    fun contarProductos(): Int {
        return productos.value?.size ?: 0
    }
}
