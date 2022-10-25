package com.example.shopbuy.data

import com.example.shopbuy.domain.ShopItem
import com.example.shopbuy.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {
    val shopList = mutableListOf<ShopItem>()
    var autoIncrementId = 0
    override fun addShopItem(shopItem: ShopItem) {

        //проверка, если добавлятся новый элемент
        if (autoIncrementId == ShopItem.UNDEFENDED_ID) {
            shopItem.id = autoIncrementId++
        }
        //это используется для редактирования
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)

    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldShopItem = getShopItemDetails(shopItem.id)
        deleteShopItem(oldShopItem)
        addShopItem(shopItem)
    }

    override fun getShopItemDetails(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Элемент $shopItemId не найден")

    }

    override fun getShopItemList(): List<ShopItem> {
        return shopList.toList()

    }
}