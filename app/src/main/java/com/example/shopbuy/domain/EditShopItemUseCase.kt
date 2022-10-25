package com.example.shopbuy.domain

import com.example.shopbuy.domain.ShopItem

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}