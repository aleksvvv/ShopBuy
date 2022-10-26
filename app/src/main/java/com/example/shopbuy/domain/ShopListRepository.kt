package com.example.shopbuy.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)
    fun deleteShopItem(shopItem: ShopItem)
    fun editShopItem(shopItem: ShopItem)
    fun getShopItemDetails(shopItemId: Int): ShopItem
    fun getShopItemList(): LiveData<List<ShopItem>>
}