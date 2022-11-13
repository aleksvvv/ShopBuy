package com.example.shopbuy.presentation

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopbuy.data.ShopListRepositoryImpl
import com.example.shopbuy.domain.*

class ShopItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ShopListRepositoryImpl(application)
    private val addItemUseCase = AddShopItemUseCase(repository)
    private val editItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemDetailsUseCase = GetShopItemDetailsUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
    get() = _shopItem

    private val _closeWindow =MutableLiveData<Unit>()
    val closeWindow:LiveData<Unit>
    get() = _closeWindow

    fun addShop(inputName: String?, inputCount: String?) {

        val name = parseName(inputName)
        val count = parseCount(inputCount)

        val resultV = inputValid(name, count)
        if (resultV) {
            val shopItem = ShopItem(name, count, true)
            addItemUseCase.addShopItem(shopItem)
            finishWork()
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)

        val resultV = inputValid(name, count)
        if (resultV) {
//            val shopItem = ShopItem(name, count, true)
            _shopItem.value?.let {
                val item =it.copy(name=name,count=count)

                editItemUseCase.editShopItem(item)
                finishWork()
            }

        }
    }

    fun getShopItem(idShopItem: Int) {
        val item = getShopItemDetailsUseCase.getShopItemDetails(idShopItem)
        _shopItem.value = item
    }

    private fun parseName(nameItem: String?): String {
        return nameItem?.trim() ?: ""
    }

    private fun parseCount(count: String?): Int {
        return try {
            count?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun inputValid(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true

            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true

            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }
    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }
    private fun finishWork(){
        _closeWindow.value = Unit
    }

}
