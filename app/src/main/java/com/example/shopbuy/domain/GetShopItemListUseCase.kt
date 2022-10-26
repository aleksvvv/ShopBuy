package com.example.shopbuy.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopbuy.domain.ShopItem
import com.example.shopbuy.presentation.MainViewModel

class GetShopItemListUseCase(private val shopListRepository: ShopListRepository) {


    fun getShopItemList(): LiveData<List<ShopItem>>{
       return shopListRepository.getShopItemList()
    }
}