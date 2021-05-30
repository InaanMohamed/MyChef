package com.mychef

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mychef.adaptor.MyAdaptor
import com.mychef.firestore.FirestoreClass
import com.mychef.models.Constants
import com.mychef.models.DataSource
import com.mychef.models.User
import kotlinx.android.synthetic.main.activity_customer_menu_list.*
import kotlinx.android.synthetic.main.layout_order_items.*

class CustomerMenuListActivity : BaseActivity(), View.OnClickListener {

    private lateinit var myAdapter: MyAdaptor
    private lateinit var userDetails: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_menu_list)
        initRecyclerView()
        addDataSet()
        setupActionBar()

        iv_Home_MenuList.setOnClickListener(this)
        iv_menu_MenuList.setOnClickListener(this)
        iv_UserProfile_MenuList.setOnClickListener(this)
        iv_order_CustomerList.setOnClickListener(this)
        tv_gold.setOnClickListener(this)
        tv_Silver.setOnClickListener(this)
    }

        private fun addDataSet(){
            val data = DataSource.createDataSet()
            myAdapter.submitList(data)
        }

        private fun initRecyclerView(){
            rv_menu_list_recycler.apply {
                layoutManager = LinearLayoutManager(this@CustomerMenuListActivity)
                val topSpaceDeco = TopSpacingItemDecoration(30)
                addItemDecoration(topSpaceDeco)
                myAdapter = MyAdaptor()
                adapter = myAdapter
            }
        }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.iv_Home_MenuList -> {
                    val intent = Intent(this@CustomerMenuListActivity, HomeActivity::class.java)
                    startActivity(intent)
                }

                R.id.iv_menu_MenuList -> {
                    Toast.makeText(
                            this@CustomerMenuListActivity,
                            resources.getString(R.string.er_msg_Already_in_Selected_Page),
                            Toast.LENGTH_SHORT
                    ).show()
                }

                R.id.iv_UserProfile_MenuList -> {
                    getUserDetails()

                }

                R.id.iv_order_CustomerList -> {
                    val intent = Intent(this@CustomerMenuListActivity, MyOrder::class.java)
                    startActivity(intent)
                }

                R.id.tv_gold -> {
                    Toast.makeText(
                            this@CustomerMenuListActivity,
                            resources.getString(R.string.No_item_in_menu),
                            Toast.LENGTH_SHORT
                    ).show()
                }

                R.id.tv_Silver -> {
                    Toast.makeText(
                            this@CustomerMenuListActivity,
                            resources.getString(R.string.No_item_in_menu),
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun userDetailSuccess(user: User) {
        userDetails=user
        val intent = Intent(this@CustomerMenuListActivity, UserProfileActivity::class.java)
        intent.putExtra(Constants.EXTRA_USER_DETAILS,userDetails )
        startActivity(intent)
    }

    private fun getUserDetails(){
        FirestoreClass().getUserDetails(this@CustomerMenuListActivity)
    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_Customer_MenuList_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.back_green)
        }
        toolbar_Customer_MenuList_activity.setNavigationOnClickListener { onBackPressed() }
    }
}
