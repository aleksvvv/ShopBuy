package com.example.shopbuy.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbuy.R
import com.example.shopbuy.databinding.ItemShopDisabledBinding
import com.example.shopbuy.databinding.ItemShopEnabledBinding
import com.example.shopbuy.domain.ShopItem

class ShopListAdapter: ListAdapter<ShopItem,ShopItemViewHolder>(ShopItemDiffCallback()) {
//
//    var onShopItemLongClickListener:OnShopItemLongClickListener? = null
//сделали через лямда
    var onShopItemLongClickListener:((ShopItem) -> Unit)? = null
    var onShopItemClickListener:((ShopItem) -> Unit)? = null

    var count = 0
    //при наследвании от ListAdapter не нужно объявлять переменную и переопределять set, т.к. вся работа скрыта
  //  var shopList = listOf<ShopItem>()
//    set(value) {
//
//        val diffUtil = ShopListDiffCallback(shopList, value)
//        //вычисления
//        val result = DiffUtil.calculateDiff(diffUtil)
//        //собщает адаптеру об изменениях
//        result.dispatchUpdatesTo(this)
//        //переопределяем сеттер. Уставливаем shopList новое значение
//        field = value
//       // notifyDataSetChanged()
//    }

//    class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
//        val tvName = view.findViewById<TextView>(R.id.tvName)
//        val tvCount = view.findViewById<TextView>(R.id.tvCount)
//    }

    override fun getItemViewType(position: Int): Int {
        val status = if (getItem(position).enable){
            STATUS_ENABLED
        }else{
            STATUS_DISABLED
        }
        return status
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

//        Log.d("onCreateViewHolder", "onCreateViewHolder ${++count}")
       val layautId = if (viewType == STATUS_ENABLED){
           R.layout.item_shop_enabled
       }else{
           R.layout.item_shop_disabled
       }

        val binding = DataBindingUtil
            .inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layautId,
            parent,
            false
        )
        //создаем вью
//        val view = LayoutInflater.from(parent.context)
//                //
//           .inflate( layautId, parent, false)
            // .inflate( R.layout.item_shop_disabled, parent, false)
        //возвращаем обьект, в котором будут выполнены действия описанные в холдере
        //вью холдер содержит view и поля tvName и tvCount
//        return ShopItemViewHolder(view)
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = holder.binding
        val status = if (shopItem.enable){"Enebled"
        }else{
            "Not enebled"
        }
        when(binding){
            is ItemShopDisabledBinding ->{
                binding.tvName.text =shopItem.name
                binding.tvCount.text = shopItem.count.toString()
            }
            is ItemShopEnabledBinding ->{
                binding.tvName.text =shopItem.name
                binding.tvCount.text = shopItem.count.toString()
            }
        }
//binding.tvName.text =shopItem.name
//        holder.tvName.text = shopItem.name
//        holder.tvName.text = "${shopItem.name }"
//        binding.tvCount.text = shopItem.count.toString()
//        holder.tvCount.text = shopItem.count.toString()

        //обрабатываем долгое нажатие

//         holder.view.setOnLongClickListener
        binding.root.setOnLongClickListener        {
        onShopItemLongClickListener?.invoke(shopItem)

             true
         }
        //обрабатываем нажатие
//        holder.view.setOnClickListener
        binding.root.setOnClickListener{
            onShopItemClickListener?.invoke(shopItem)
        }

//        if (shopItem.enable){
//            holder.tvName.setTextColor(ContextCompat.getColor(holder.view.context, android.R.color.holo_red_dark))
//        }else{
//            holder.tvName.setTextColor(ContextCompat.getColor(holder.view.context, android.R.color.white))
//        }
    }
//   сделали через лямбда выражение
//    interface OnShopItemLongClickListener{
//        fun onShopItemLongClick(shopItem: ShopItem){}
//    }

companion object{
    const val STATUS_ENABLED = 1
    const val STATUS_DISABLED = -1
    const val MAX_POOL_ENABLED = 60
}
//при наследвании от ListAdapter этот метод не нужен, т.к. вся работа скрыта
//    override fun getItemCount(): Int {
//        return shopList.size
//    }
}