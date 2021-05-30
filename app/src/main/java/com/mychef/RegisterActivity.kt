package com.mychef


import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mychef.firestore.FirestoreClass
import com.mychef.models.User
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register.setOnClickListener {
            registerUser()
        }
    }


    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(et_first_name.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.er_msg_enter_first_name), true)
                false
            }

            TextUtils.isEmpty(et_Last_name.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.er_msg_enter_last_name), true)
                false
            }

            TextUtils.isEmpty(et_EmailID.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.er_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(et_Address.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.er_msg_Address), true)
                false
            }

            TextUtils.isEmpty(et_Password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(
                    resources.getString(R.string.er_msg_enter_password), true
                )
                false
            }

            TextUtils.isEmpty(et_ConfrimPassword.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(
                    resources.getString(R.string.er_msg_enter_confirm_password), true
                )
                false
            }

            et_Password.text.toString().trim { it <= ' ' } != et_ConfrimPassword.text.toString()
                .trim { it <= ' ' } -> {
                showErrorSnackBar(
                    resources.getString(R.string.er_msg_password_and_confirm_password_mismatch), true
                )
                false
            }

            !cb_terms_and_condition.isChecked -> {
                showErrorSnackBar(
                    resources.getString(R.string.er_msg_agree_terms_and_condition), true
                )
                false
            }
            else -> {
                true
            }
        }
    }


    private fun registerUser() {
        if (validateRegisterDetails()) {
            showProgressDialog(resources.getString(R.string.please_wait))

            val email: String = et_EmailID.text.toString().trim { it <= ' ' }
            val password: String = et_Password.text.toString().trim { it <= ' ' }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {

                            val firebaseUser: FirebaseUser = task.result!!.user!!

                            val user = User(
                                firebaseUser.uid,
                                et_first_name.text.toString().trim { it <= ' ' },
                                et_Last_name.text.toString().trim { it <= ' ' },
                                et_EmailID.text.toString().trim { it <= ' ' },
                                et_Address.text.toString().trim { it <= ' ' }
                            )
                            FirestoreClass().registerUser(this@RegisterActivity, user)
                        }
                        else {
                            hideProgressDialog()
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                }
            )
        }
    }


    fun userRegistrationSuccess() {

        hideProgressDialog()

        Toast.makeText(
                this@RegisterActivity,
                resources.getString(R.string.Successful_msg_Registered),
                Toast.LENGTH_SHORT
        ).show()
        FirebaseAuth.getInstance().signOut()
        finish()
    }
}