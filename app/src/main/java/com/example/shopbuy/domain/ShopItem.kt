package com.example.shopbuy.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val enable: Boolean,
    var id: Int = UNDEFENDED_ID
){
    companion object{
        const val UNDEFENDED_ID = -1
    }
}
