package com.example.commerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.commerce.Components.CartViewModel
import com.example.commerce.ui.CartScreen
import com.example.commerce.ui.theme.CommerceTheme

class MainActivity : ComponentActivity() {

    private val cartViewModel: CartViewModel by viewModels() // Proper ViewModel initialization

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CommerceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CartScreen(viewModel = cartViewModel) // Ensure CartScreen is displayed
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCartScreen() {
    CommerceTheme {
        val previewViewModel = CartViewModel()
        CartScreen(viewModel = previewViewModel) // Use correct composable name
    }
}


