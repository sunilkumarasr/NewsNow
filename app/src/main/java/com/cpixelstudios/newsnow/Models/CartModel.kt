package com.cpixelstudios.newsnow.Models

data class CartModel(
    val imageResId: Int,
    val title: String,
    val actualPrice: String,
    val offerPrice: String,
    val rating: Int
)
