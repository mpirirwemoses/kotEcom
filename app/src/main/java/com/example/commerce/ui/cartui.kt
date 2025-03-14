package com.example.commerce.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.commerce.Components.CartViewModel
import com.example.commerce.Components.CartItem

@Composable
fun CartScreen(viewModel: CartViewModel) {
    // Accessing cart items from ViewModel
    val cartItems by remember { derivedStateOf { viewModel.cartItems } }
    val totalPrice by remember { derivedStateOf { viewModel.totalPrice } }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Shopping Cart", style = MaterialTheme.typography.headlineMedium)

        // Displaying cart items using LazyColumn
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(cartItems) { cartItem ->  // Accessing and displaying each cart item
                CartItemRow(cartItem, viewModel)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Displaying total price
        Text(
            text = "Total: $${totalPrice}",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black,
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Checkout Button
        Button(
            onClick = { /* Handle Checkout */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722))
        ) {
            Text("Proceed to Checkout", fontSize = 18.sp)
        }
    }
}

@Composable
fun CartItemRow(cartItem: CartItem, viewModel: CartViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { viewModel.removeFromCart(cartItem) }, // Removing item on click
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Displaying product image
            cartItem.product.imageUrl?.let { imageUrl ->
                Image(
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = cartItem.product.name,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            // Displaying product details
            Column(modifier = Modifier.weight(1f)) {
                Text(text = cartItem.product.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = "x${cartItem.quantity}", style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Displaying product price
            Text(text = "$${cartItem.product.price * cartItem.quantity}", fontSize = 18.sp)
        }
    }
}
