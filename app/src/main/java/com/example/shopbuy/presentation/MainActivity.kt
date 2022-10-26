package com.example.shopbuy.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.shopbuy.R
import com.example.shopbuy.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this){
            Log.d("MyLog", it.toString())
            if (count == 0){
                count++
        val item = it[0]
            viewModel.changeEnabled(item)
          Log.d("MyLog", it.toString())
            }
        }
//        viewModel.getShopList()

//        viewModel.shopList.observe(this) {
//            Log.d("MyLog", it.toString())
//        }
//        viewModel.deleteShopItem(ShopItem("tt 0", 0, true))
    }

}