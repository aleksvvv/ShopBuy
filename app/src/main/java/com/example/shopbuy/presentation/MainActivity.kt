package com.example.shopbuy.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbuy.R
import com.example.shopbuy.domain.ShopItem
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShopListAdapter
    //private var count = 0
  //  private lateinit var llShopList:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//получаем ссылку на LinearLayout
     //   llShopList = findViewById( R.id.ll_shop_list)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this){
            //в адаптер вставляем новый лист
            adapter.shopList = it
//            Log.d("MyLog", it.toString())
//            if (count == 0){
//                count++
//        val item = it[0]
//            viewModel.changeEnabled(item)
//          Log.d("MyLog", it.toString())
//            }

          //  showList(it)
        }

//        viewModel.getShopList()

//        viewModel.shopList.observe(this) {
//            Log.d("MyLog", it.toString())
//        }
//        viewModel.deleteShopItem(ShopItem("tt 0", 0, true))
    }
    private fun setupRecyclerView(){
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        adapter = ShopListAdapter()
        rvShopList.adapter = adapter
        rvShopList.recycledViewPool.setMaxRecycledViews(ShopListAdapter.STATUS_ENABLED, ShopListAdapter.MAX_POOL_ENABLED)
        rvShopList.recycledViewPool.setMaxRecycledViews(ShopListAdapter.STATUS_DISABLED, ShopListAdapter.MAX_POOL_ENABLED)
//создаем объект анонимного класса
//        adapter.onShopItemLongClickListener = object: ShopListAdapter.OnShopItemLongClickListener{
//            //переопределяем метод
//            override fun onShopItemLongClick(shopItem: ShopItem) {
//                viewModel.changeEnabled(shopItem)
//            }
//        }
        //сразу вызываем метод через лямбду
        adapter.onShopItemLongClickListener =  {
            viewModel.changeEnabled(it)
        }
        adapter.onShopItemClickListener = {
            Log.d("MyLog", "it: $it")
        }
        swipeDelete(rvShopList)
    }
fun swipeDelete(rvShopList:RecyclerView){
    //метод для удаления по свайпу. Создаем callback через анонимный класс
    //переопределяем метод  onSwiped, в методе onMove делаем return false
    //создаем объект анонимного класса
    val callback = object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val item = adapter.shopList[viewHolder.absoluteAdapterPosition]
            viewModel.deleteShopItem(item)
        }
    }
    //создаем объект ItemTouchHelper
    val itemTouchHelper = ItemTouchHelper(callback)
    //прикрепляем его к рецайклервью
    itemTouchHelper.attachToRecyclerView(rvShopList)
}



// private fun showList(list: List<ShopItem>){
//     //очистим список перед обновлением
//     llShopList.removeAllViews()
//
////проходим все элементы листа
//     for (itemShop in list) {
//         //получаем айди лаяута
//         val layoutId = if (itemShop.enable){
//             R.layout.item_shop_enabled
//         } else{
//             R.layout.item_shop_disabled
//         }
//         //получаем вью
//         val view = LayoutInflater.from(this)
//             .inflate( layoutId, llShopList, false)
//         //нашли элементы
//         val tvName = view.findViewById<TextView>(R.id.tvName)
//         val tvCount = view.findViewById<TextView>(R.id.tvCount)
//         //ставим им значения
//         tvName.text = itemShop.name
//         tvCount.text = itemShop.count.toString()
//         //обрабатываем долгое нажатие
//         view.setOnLongClickListener {
//             viewModel.changeEnabled(itemShop)
//             true
//         }
//         //вставляем вью в LinearLayout
//         llShopList.addView(view)
//     }
//
// }
}