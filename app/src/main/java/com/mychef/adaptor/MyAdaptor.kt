package com.mychef.adaptor

import android.content.Intent
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.common.graph.MutableNetwork
import com.mychef.MyOrder
import com.mychef.R
import com.mychef.UserProfileActivity
import com.mychef.models.MenuListItems
import com.mychef.models.OrderCart
import kotlinx.android.synthetic.main.layout_menu_list_items.view.*

class MyAdaptor: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

   private var MenuList: List<MenuListItems> = ArrayList()
    /*private var onClickListener: OnClickListener? = null*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  MenuViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_menu_list_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MenuViewHolder ->{
                holder.bind(MenuList.get(position))

            }
        }
    }

    override fun getItemCount(): Int {
        return MenuList.size
    }

    /*fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {

        fun onClick(position: Int, menuItem: MenuListItems)
    }*/


    fun submitList(menuList: List<MenuListItems>){
        MenuList = menuList
    }

    class MenuViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val menuImage = itemView.menu_image
        val menuTitle = itemView.menu_title
        val menuPrice = itemView.menu_price
        val orderbutton = itemView.btn_AddOrder

        fun bind(MenulistItems:MenuListItems){
            menuTitle.setText(MenulistItems.Title)
            menuPrice.setText(MenulistItems.Price)

            orderbutton.setOnClickListener {
                OrderCart.cartItems.add(MenulistItems)

                val intent = Intent(itemView.context, MyOrder::class.java)
                itemView.context.startActivity(intent)
            }




            val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(MenulistItems.image)
                    .into(menuImage)
        }
    }

}