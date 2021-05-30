package com.mychef

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.mychef.firestore.FirestoreClass
import com.mychef.models.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

@Suppress("DEPRECATION")
class LoginActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tv_ForgotPassword_LoginActivity.setOnClickListener(this)
        btn_Login_Loginactivity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.tv_ForgotPassword_LoginActivity -> {
                    val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
                    startActivity(intent)
                }

                R.id.btn_Login_Loginactivity -> {
                   logInRegisteredUser()

                }
            }
        }
    }


    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(et_EmailID_loginPage.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.er_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(et_Password_LoginPage.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.er_msg_enter_password), true)
                false
            }
            else -> {
                showErrorSnackBar("Details valid.", false)
                true

            }
        }
    }

    private fun logInRegisteredUser() {

        if (validateLoginDetails()) {

            showProgressDialog(resources.getString(R.string.please_wait))

            val email = et_EmailID_loginPage.text.toString().trim { it <= ' ' }
            val password = et_Password_LoginPage.text.toString().trim { it <= ' ' }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        FirestoreClass().getUserDetails(this@LoginActivity)
                    }
                    else {
                        hideProgressDialog()
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }
        }
    }

    fun userLoggedInSuccess(user: User) {
        hideProgressDialog()
        Log.i("First Name: ", user.firstName)
        Log.i("Last Name: ", user.lastName)
        Log.i("Address: ", user.address)
        Log.i("Email: ", user.email)
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        finish()
    }
}
