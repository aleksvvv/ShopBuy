package com.example.shopbuy.presentation

import android.app.Application
import androidx.lifecycle.*
import com.example.shopbuy.data.ShopListRepositoryImpl
import com.example.shopbuy.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

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
    viewModelScope.launch {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

//       getShopList()
    }
    fun changeEnabled(shopItem: ShopItem){
//        shopItem.enable = false
//        editShopItemUseCase.editShopItem(shopItem)
        viewModelScope.launch {
            val newShop = shopItem.copy(enable = !shopItem.enable )
            editShopItemUseCase.editShopItem(newShop)
        }

//        getShopList()
    }


}