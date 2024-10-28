package com.example.listadelacompra.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.listadelacompra.viewmodel.ProductoViewModel
import com.example.listadelacompra.data.Producto

@Composable
fun MainScreen(productoViewModel: ProductoViewModel) {
    val productos by productoViewModel.productos.observeAsState(emptyList())
    val totalPrecio = productoViewModel.calcularTotal()
    val totalProductos = productoViewModel.contarProductos()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Total de productos: $totalProductos")
        Text("Precio total: $totalPrecio €")  // Agrega el símbolo del euro

        Spacer(modifier = Modifier.height(16.dp))

        productos.forEach { producto ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Producto: ${producto.nombre} - Cantidad: ${producto.cantidad ?: "-"} - Precio: ${producto.precio?.let { "$it €" } ?: "-"}")

                IconButton(onClick = {
                    productoViewModel.eliminarProducto(producto)
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        FormularioProducto(onSubmit = { nombre, cantidad, precio ->
            productoViewModel.agregarProducto(
                Producto(nombre = nombre, cantidad = cantidad, precio = precio)
            )
        })
    }
}
