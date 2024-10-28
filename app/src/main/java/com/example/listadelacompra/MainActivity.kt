package com.example.listadelacompra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.listadelacompra.data.ProductoDatabase
import com.example.listadelacompra.repository.ProductoRepository
import com.example.listadelacompra.ui.MainScreen
import com.example.listadelacompra.ui.theme.ListaDeLaCompraTheme
import com.example.listadelacompra.viewmodel.ProductoViewModel
import com.example.listadelacompra.viewmodel.ProductoViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = ProductoDatabase.getDatabase(this)
        val repository = ProductoRepository(database.productoDao())

        val productoViewModelFactory = ProductoViewModelFactory(repository)
        val productoViewModel = ViewModelProvider(this, productoViewModelFactory).get(ProductoViewModel::class.java)

        setContent {
            ListaDeLaCompraTheme {
                MainScreen(productoViewModel = productoViewModel)
            }
        }
    }
}