package com.example.shopbuy.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopbuy.data.ShopListRepositoryImpl
import com.example.shopbuy.domain.*

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ShopListRepositoryImpl(application)

    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val getShopItemListUseCase = GetShopItemListUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopItemListUseCase.getShopItemList()

//    fun getShopList() {
//        val list = getShopItemListUseCase.getShopItemList()
//        shopList.value = list
//    }
    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
//       getShopList()
    }
    fun changeEnabled(shopItem: ShopItem){
//        shopItem.enable = false
//        editShopItemUseCase.editShopItem(shopItem)
        val newShop = shopItem.copy(enable = !shopItem.enable )
        editShopItemUseCase.editShopItem(newShop)
//        getShopList()
    }


}