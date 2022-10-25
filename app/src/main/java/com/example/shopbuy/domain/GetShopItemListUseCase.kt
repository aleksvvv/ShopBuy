package com.example.shopbuy.domain

import com.example.shopbuy.domain.ShopItem

class GetShopItemListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItemList(): List<ShopItem>{
       return shopListRepository.getShopItemList()
    }
}