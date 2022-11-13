package com.example.shopbuy.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.shopbuy.domain.ShopItem
import com.example.shopbuy.domain.ShopListRepository
import kotlin.random.Random

class ShopListRepositoryImpl(application: Application) : ShopListRepository {
    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val mapper = ShopMapper()

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()

    // private val shopList = mutableListOf<ShopItem>()

//    private val shopList = sortedSetOf<ShopItem>(object : Comparator<ShopItem>{
//    override fun compare(p0: ShopItem, p1: ShopItem): Int {
//        return p0.id.compareTo(p1.id)
//
//            }

    //    private val shopList = sortedSetOf<ShopItem>({ p0, p1 -> p0.id.compareTo(p1.id) })
//
    private var autoIncrementId = 0
//
//    init {
//        for (i in 0 until 5) {
//            val item = ShopItem("name $i", i, Random.nextBoolean())
//            addShopItem(item)
//        }
//    }

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.shopItemEntryToShopItemDb(shopItem))
//        //проверка, если добавлятся новый элемент
//        if (shopItem.id == ShopItem.UNDEFENDED_ID) {
//            shopItem.id = autoIncrementId++
//        }
//        //это используется для редактирования
//        shopList.add(shopItem)
//        //обновляем ливдату
//        updateList()
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.delShopItem(shopItem.id)

//        shopList.remove(shopItem)
//        //обновляем ливдату
//        updateList()
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.shopItemEntryToShopItemDb(shopItem))
//        val oldShopItem = getShopItemDetails(shopItem.id)
//        deleteShopItem(oldShopItem)
//        addShopItem(shopItem)
    }

    override suspend fun getShopItemDetails(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.shopItemDbToShopItemEntry(dbModel)
//        return shopList.find {
//            it.id == shopItemId
//        } ?: throw RuntimeException("Элемент $shopItemId не найден")

    }

//    override fun getShopItemList(): LiveData<List<ShopItem>> {
//        return MediatorLiveData<List<ShopItem>>().apply {
//            addSource(shopListDao.getShopList()) {
//                value = mapper.shopListDbToShopListEntry(it)
//            }
//        }
//    }
    //Второй вариант, более короткий
override fun getShopItemList(): LiveData<List<ShopItem>> {
   return Transformations.map(
        shopListDao.getShopList()){
        mapper.shopListDbToShopListEntry(it)
    }
}


//    private fun updateList() {
//        //в лив дату присваивмаем копию списка
//        shopListLD.value = shopList.toList()
//    }
}