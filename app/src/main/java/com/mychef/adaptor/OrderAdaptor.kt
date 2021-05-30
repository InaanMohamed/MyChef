package com.mychef.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mychef.R
import com.mychef.models.MenuListItems
import com.mychef.models.OrderCart
import kotlinx.android.synthetic.main.layout_order_items.view.*

class OrderAdaptor: RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  MenuViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_order_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MenuViewHolder ->{
                holder.bind(OrderCart.cartItems.get(position))

            }
        }
    }

    override fun getItemCount(): Int {
        return OrderCart.cartItems.size
    }

    class MenuViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val menuImage = itemView.menu_image
        val menuTitle = itemView.menu_title
        val menuPrice = itemView.menu_price

        fun bind(MenulistItems: MenuListItems){
            menuTitle.setText(MenulistItems.Title)
            menuPrice.setText(MenulistItems.Price)

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