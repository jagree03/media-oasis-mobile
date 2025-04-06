package com.example.jagree03.mediaoasis.Model
import java.io.Serializable

data class Product(
    val prodId: Int,
    var prodName: String,
    var prodDescription: String?,
    var prodPrice: Double,
	var prodImage: ByteArray?,
    var prodCategory: String,
    var prodIsAvailable: Boolean) : Serializable {
}