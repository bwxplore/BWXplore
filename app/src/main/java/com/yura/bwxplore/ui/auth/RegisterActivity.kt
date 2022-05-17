package com.yura.bwxplore.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.yura.bwxplore.MainActivity
import com.yura.bwxplore.R
import com.yura.bwxplore.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        with(binding) {
            btnLogin.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }

            btnRegister.setOnClickListener {
                if (inputName.text.isNotEmpty() && inputEmail.text.isNotEmpty()
                    && inputPassword.text.isNotEmpty() && inputPasswordRepeat.text.isNotEmpty()
                ) {
                    if (inputPasswordRepeat.text.toString() == inputPassword.text.toString()) {
                        register(
                            inputName.text.toString(),
                            inputEmail.text.toString(),
                            inputPassword.text.toString()
                        )
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            getString(R.string.confirmation_incorrect),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        getString(R.string.pls_fill_all),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }

    private fun register(name: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = task.result.user
                    if (user != null) {
                        val profileUpdates = userProfileChangeRequest {
                            displayName = name
                        }

                        user.updateProfile(profileUpdates)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    reload()
                                }
                            }
                    } else {
                        Toast.makeText(applicationContext, "Register Failed!", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        applicationContext,
                        task.exception?.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun reload() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }
}