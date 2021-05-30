package com.mychef

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)


        btn_Send_ForgotPassword.setOnClickListener {
            val email: String = et_Email_to_ResetPassword.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                showErrorSnackBar(resources.getString(R.string.er_msg_enter_email), true)
            } else {
                showProgressDialog(resources.getString(R.string.please_wait))

                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            hideProgressDialog()
                            if (task.isSuccessful) {
                                Toast.makeText(
                                        this@ForgotPasswordActivity,
                                        resources.getString(R.string.Successful_msg_ResetEmailSent),
                                        Toast.LENGTH_LONG
                                ).show()

                                finish()
                            } else {
                                showErrorSnackBar(task.exception!!.message.toString(), true)
                            }
                        }
            }

        }
    }
}




