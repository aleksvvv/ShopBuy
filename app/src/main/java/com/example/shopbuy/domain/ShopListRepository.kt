package com.example.shopbuy.domain

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)
    fun deleteShopItem(shopItem: ShopItem)
    fun editShopItem(shopItem: ShopItem)
    fun getShopItemDetails(shopItemId: Int): ShopItem
    fun getShopItemList(): List<ShopItem>
}