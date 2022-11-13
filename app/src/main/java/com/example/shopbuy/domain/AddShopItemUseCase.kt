package com.example.shopbuy.domain

import com.example.shopbuy.domain.ShopItem

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun addShopItem(shopItem: ShopItem){
        shopListRepository.addShopItem(shopItem)
    }
}