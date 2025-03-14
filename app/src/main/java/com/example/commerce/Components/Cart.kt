package com.example.commerce.Components

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class CartViewModel : ViewModel() {

    var products = mutableStateListOf<Product>()
    var cartItems = mutableStateListOf<CartItem>()
    var totalPrice by mutableStateOf(0.0)

    init {
        loadProducts()
        loadCartItems() // Generate 5 dummy products in the cart
    }

    private fun loadProducts() {
        // Load sample products (In a real app, this would come from a database or API)
        products.add(Product(1, "Laptop", "A high-performance laptop", 1000.0, "laptop_image_url"))
        products.add(Product(2, "Smartphone", "A sleek and modern smartphone", 500.0, "smartphone_image_url"))
        products.add(Product(3, "Headphones", "Noise-canceling headphones", 200.0, "headphones_image_url"))
        products.add(Product(4, "Smartwatch", "A stylish smartwatch", 150.0, "smartwatch_image_url"))
        products.add(Product(5, "Tablet", "A lightweight and powerful tablet", 700.0, "tablet_image_url"))
    }

    private fun loadCartItems() {
        // Adding 5 dummy products to the cart
        cartItems.add(CartItem(products[0], 1)) // Laptop
        cartItems.add(CartItem(products[1], 2)) // Smartphone (Quantity 2)
        cartItems.add(CartItem(products[2], 1)) // Headphones
        cartItems.add(CartItem(products[3], 3)) // Smartwatch (Quantity 3)
        cartItems.add(CartItem(products[4], 1)) // Tablet

        calculateTotal()
    }

    fun addToCart(product: Product) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            cartItems.add(CartItem(product, 1))
        }
        calculateTotal()
    }

    fun removeFromCart(cartItem: CartItem) {
        cartItems.remove(cartItem)
        calculateTotal()
    }

    private fun calculateTotal() {
        totalPrice = cartItems.sumOf { it.product.price * it.quantity }
    }
}
