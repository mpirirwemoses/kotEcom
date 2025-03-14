package com.example.commerce.Components

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
)
data class CartItem(
    val product: Product,
    var quantity: Int

)
