package com.example.shopbuy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopbuy.domain.ShopItem
import com.example.shopbuy.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {
    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0

    init {
        for (i in 0 until 10){
            val item = ShopItem("tt $i", i, true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        //проверка, если добавлятся новый элемент
        if (shopItem.id == ShopItem.UNDEFENDED_ID) {
            shopItem.id = autoIncrementId++
        }
        //это используется для редактирования
        shopList.add(shopItem)
        //обновляем ливдату
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        //обновляем ливдату
        updateList()
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
        override fun getShopItemList(): LiveData<List<ShopItem>> {
        return shopListLD
    }
    private fun updateList(){
        //в лив дату присваивмаем копию списка
        shopListLD.value = shopList.toList()
    }
}