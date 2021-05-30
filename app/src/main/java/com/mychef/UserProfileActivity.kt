package com.mychef

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.mychef.firestore.FirestoreClass
import com.mychef.models.Constants
import com.mychef.models.User
import kotlinx.android.synthetic.main.activity_customer_menu_list.*
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : BaseActivity(), View.OnClickListener {

    private lateinit var userDetails: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        if(intent.hasExtra(Constants.EXTRA_USER_DETAILS)) {
            userDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }
        setupActionBar()
        et_first_name_userprofile.isEnabled = false
        et_first_name_userprofile.setText(userDetails.firstName)

        et_Last_name_userprofile.isEnabled = false
        et_Last_name_userprofile.setText(userDetails.lastName)

        et_EmailID_userprofile.isEnabled = false
        et_EmailID_userprofile.setText(userDetails.email)

        btn_save.setOnClickListener(this)
        iv_Home_editprofile.setOnClickListener(this)
        iv_menu_editprofile.setOnClickListener(this)
        iv_order_editprofile.setOnClickListener(this)
        iv_UserProfile_editprofile.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.btn_save -> {

                    if (validateUserProfileDetails()) {

                        val userHashMap = HashMap<String, Any>()
                        val useraddress = et_Address_userprofile.text.toString().trim { it <= ' ' }

                        if (useraddress.isNotEmpty()){
                            userHashMap[Constants.ADDRESS] = useraddress.toString()
                        }

                        showProgressDialog(resources.getString(R.string.please_wait))
                       FirestoreClass().updateUserProfileData(this,userHashMap)
                        }
                    }

                R.id.iv_Home_editprofile -> {
                    val intent = Intent(this@UserProfileActivity, HomeActivity::class.java)
                    startActivity(intent)
                }

                R.id.iv_menu_editprofile -> {
                    val intent = Intent(this@UserProfileActivity, CustomerMenuListActivity::class.java)
                    startActivity(intent)
                }

                R.id.iv_order_editprofile -> {
                    val intent = Intent(this@UserProfileActivity, MyOrder::class.java)
                    startActivity(intent)
                }

                R.id.iv_UserProfile_editprofile -> {
                    Toast.makeText(
                            this@UserProfileActivity,
                            resources.getString(R.string.er_msg_Already_in_Selected_Page),
                            Toast.LENGTH_SHORT
                    ).show()

                }
                }
            }
        }


    fun userProfileUpdateSuccess() {

        hideProgressDialog()

        Toast.makeText(
            this@UserProfileActivity,
            resources.getString(R.string.Successful_msg_Updated),
            Toast.LENGTH_SHORT
        ).show()

        startActivity(Intent(this@UserProfileActivity, HomeActivity::class.java))
        finish()
    }

    private fun validateUserProfileDetails(): Boolean {
        return when {

            TextUtils.isEmpty(et_Address_userprofile.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.er_msg_Address), true)
                false
            }
            else -> {
                true
            }
        }
    }
    private fun setupActionBar() {

        setSupportActionBar(toolbar_UserProfile_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.back_green)
        }
        toolbar_UserProfile_activity.setNavigationOnClickListener { onBackPressed() }
    }

    }



