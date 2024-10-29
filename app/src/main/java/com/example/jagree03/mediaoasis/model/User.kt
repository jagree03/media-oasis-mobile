package com.example.jagree03.mediaoasis.model

data class User(
    var userID: Int,
    var userFirstName: String,
    var userLastName: String,
    var userEmail: String,
    var userPhoneNo: String,
    var userUserName: String,
    var userPassWord: String,
    val userIsAdmin: Int) {
}