package com.example.shopbuy.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    suspend fun addShopItem(shopItem: ShopItem)
    suspend fun deleteShopItem(shopItem: ShopItem)
    suspend fun editShopItem(shopItem: ShopItem)
    suspend fun getShopItemDetails(shopItemId: Int): ShopItem
    fun getShopItemList(): LiveData<List<ShopItem>>
}