package com.example.jagree03.mediaoasis.Model
import java.io.Serializable

data class Product(
    val ProdId: Int, var ProdName: String, var ProdDescription: String, var ProdPrice: Double,
	var ProdImage: ByteArray?, var ProdAvailable: Int, var ProdCategory: String) : Serializable {
}