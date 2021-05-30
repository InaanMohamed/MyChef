package com.mychef

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mychef.firestore.FirestoreClass
import com.mychef.models.Constants
import com.mychef.models.User
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(),View.OnClickListener {

    private lateinit var userDetails: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btn_Menu_HomePage.setOnClickListener(this)
        btn_Chef_HomePage.setOnClickListener(this)
        iv_Home_HomePage.setOnClickListener(this)
        iv_menu_HomePage.setOnClickListener(this)
        iv_order_HomePage.setOnClickListener(this)
        iv_UserProfile_HomePage.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {

                R.id.btn_Menu_HomePage -> {
                    val intent = Intent(this@HomeActivity, CustomerMenuListActivity::class.java)
                    startActivity(intent)
                }

                R.id.btn_Chef_HomePage -> {
                    Toast.makeText(
                        this@HomeActivity,
                        resources.getString(R.string.Chefs_Not_ready),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                R.id.iv_Home_HomePage -> {
                    Toast.makeText(
                            this@HomeActivity,
                            resources.getString(R.string.er_msg_Already_in_Selected_Page),
                            Toast.LENGTH_SHORT
                    ).show()
                }

                R.id.iv_menu_HomePage -> {
                    val intent = Intent(this@HomeActivity, CustomerMenuListActivity::class.java)
                    startActivity(intent)
                }

                R.id.iv_order_HomePage -> {
                    val intent = Intent(this@HomeActivity, MyOrder::class.java)
                    startActivity(intent)
                }

                R.id.iv_UserProfile_HomePage -> {
                   getUserDetails()
                }
            }
        }
    }

    fun userDetailSuccess(user: User) {
        userDetails=user
        val intent = Intent(this@HomeActivity, UserProfileActivity::class.java)
        intent.putExtra(Constants.EXTRA_USER_DETAILS,userDetails )
        startActivity(intent)
    }

    private fun getUserDetails(){
        FirestoreClass().getUserDetails(this@HomeActivity)
    }

}