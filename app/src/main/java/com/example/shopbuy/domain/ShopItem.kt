package com.example.shopbuy.domain

data class ShopItem(
    var name: String,
    val count: Int,
    var enable: Boolean,
    var id: Int = UNDEFENDED_ID
){
    companion object{
        const val UNDEFENDED_ID = 0
    }
}
