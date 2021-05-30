package com.mychef

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mychef.adaptor.OrderAdaptor
import com.mychef.firestore.FirestoreClass
import com.mychef.models.Constants
import com.mychef.models.User
import kotlinx.android.synthetic.main.activity_customer_menu_list.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_my_order.*
import kotlinx.android.synthetic.main.activity_my_order.rv_menu_list_recycler
import kotlinx.android.synthetic.main.activity_user_profile.*

class MyOrder : BaseActivity(), View.OnClickListener {

    private lateinit var userDetails: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_order)
        initRecyclerView()
        setupActionBar()
        iv_Home_OrderList.setOnClickListener(this)
        iv_menu_OrderList.setOnClickListener(this)
        iv_order_OrderList.setOnClickListener(this)
        iv_UserProfile_OrderList.setOnClickListener(this)
    }

    private fun initRecyclerView(){
        rv_menu_list_recycler.apply {
            layoutManager = LinearLayoutManager(this@MyOrder)
            val topSpaceDeco = TopSpacingItemDecoration(30)
            addItemDecoration(topSpaceDeco)
            adapter = OrderAdaptor()
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.iv_Home_OrderList -> {
                    val intent = Intent(this@MyOrder, HomeActivity::class.java)
                    startActivity(intent)
                }

                R.id.iv_menu_OrderList -> {
                    val intent = Intent(this@MyOrder, CustomerMenuListActivity::class.java)
                    startActivity(intent)
                }

                R.id.iv_order_OrderList -> {
                    Toast.makeText(
                            this@MyOrder,
                            resources.getString(R.string.er_msg_Already_in_Selected_Page),
                            Toast.LENGTH_SHORT
                    ).show()
                }

                R.id.iv_UserProfile_OrderList -> {
                    getUserDetails()
                }


            }
        }
    }

    fun userDetailSuccess(user: User) {
        userDetails=user
        val intent = Intent(this@MyOrder, UserProfileActivity::class.java)
        intent.putExtra(Constants.EXTRA_USER_DETAILS,userDetails )
        startActivity(intent)
    }

    private fun getUserDetails(){
        FirestoreClass().getUserDetails(this@MyOrder)
    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_MyOrder_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.back_green)
        }
        toolbar_MyOrder_activity.setNavigationOnClickListener { onBackPressed() }
    }
}
