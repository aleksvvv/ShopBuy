package com.example.shopbuy.domain

import com.example.shopbuy.domain.ShopItem

class GetShopItemDetailsUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItemDetails(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItemDetails(shopItemId)
    }
}