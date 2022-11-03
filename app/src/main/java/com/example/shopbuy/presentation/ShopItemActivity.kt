package com.example.shopbuy.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.shopbuy.R
import com.example.shopbuy.domain.ShopItem
import com.google.android.material.textfield.TextInputLayout

class ShopItemActivity : AppCompatActivity() {
    //    private lateinit var viewModel: ShopItemViewModel
//
//    private lateinit var tilName: TextInputLayout
//    private lateinit var tilCount: TextInputLayout
//    private lateinit var etName: EditText
//    private lateinit var etCount: EditText
//    private lateinit var bInpute: Button
    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFENDED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)

        parseIntent()
//        viewModel = ViewModelProvider(this).get(ShopItemViewModel::class.java)
//        initName()
//        addTextChangeListener()

//        val mode = intent.getStringExtra(EXTRA_MODE_SCREEN)
//        val modeId = intent.getStringExtra(EXTRA_EDIT_ID)
//        Log.d("ShopItemActivity", mode.toString())
        if (savedInstanceState == null) {
            launchRightMode()
        }
//        observeViewModel()
    }

    //    private fun observeViewModel(){
//        viewModel.errorInputCount.observe(this) {
//            val message = if (it) {"Неправильный ввод числа"}
//            else {null}
//            tilCount.error = message
//        }
//        viewModel.errorInputName.observe(this) {
//            val message = if (it)  {"Неправильный ввод имени"}
//            else {null}
//            tilName.error = message
//        }
//        viewModel.closeWindow.observe(this) {
//            finish()
//        }
//    }
    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_ADD -> ShopItemFragment.newInstanceAddItem()
            MODE_EDIT -> ShopItemFragment.newInstanceEditItem(shopItemId)
            else -> throw RuntimeException(" Экран отутствует")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container, fragment)
            .commit()
    }

    //    private fun addTextChangeListener(){
//        etCount.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputCount()
//            }
//            override fun afterTextChanged(p0: Editable?) {
//            }
//        })
//        etName.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputName()
//            }
//            override fun afterTextChanged(p0: Editable?) {
//            }
//        })
//    }
//    private fun launchEditMode() {
//        //  получим объект
//        viewModel.getShopItem(shopItemId)
//        //подпишемся на объект
//        viewModel.shopItem.observe(this) {
//            etName.setText(it.name)
//            etCount.setText(it.count.toString())
//        }
//
//        bInpute.setOnClickListener {
//            viewModel.editShopItem(etName.text?.toString(), etCount.text?.toString())
//
//        }
//    }
//
//    private fun launchAddMode() {
//        bInpute.setOnClickListener() {
//            viewModel.addShop(etName.text?.toString(), etCount.text?.toString())
//        }
//    }
//
//    private fun initName() {
//        tilName = findViewById(R.id.tilName)
//        tilCount = findViewById(R.id.tilCount)
//        etName = findViewById(R.id.etName)
//        etCount = findViewById(R.id.etCount)
//        bInpute = findViewById(R.id.bInput)
//    }
//
    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_MODE_SCREEN)) {
            throw RuntimeException("Параметры отутствуют")
        }
        val mode = intent.getStringExtra(EXTRA_MODE_SCREEN)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Режим $mode не установлен")
        }
        screenMode = mode
        if (mode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_EDIT_ID)) {
                throw RuntimeException("id отсутствует")
            }
            shopItemId = intent.getIntExtra(EXTRA_EDIT_ID, ShopItem.UNDEFENDED_ID)
        }
    }

    companion object {
        private const val EXTRA_MODE_SCREEN = "extra_mode_screen"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""
        private const val EXTRA_EDIT_ID = "extra_edit_id"


        fun putModeAdd(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE_SCREEN, MODE_ADD)
            return intent
        }

        fun putModeEdit(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE_SCREEN, MODE_EDIT)
            intent.putExtra(EXTRA_EDIT_ID, shopItemId)
            return intent
        }
    }
}