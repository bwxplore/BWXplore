package com.yura.bwxplore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yura.bwxplore.databinding.ActivityOnboardingBinding
import com.yura.bwxplore.ui.auth.LoginActivity
import com.yura.bwxplore.ui.detail.MapsActivity

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetStarted.setOnClickListener {
            startActivity(Intent(baseContext, LoginActivity::class.java))
        }
    }

    private fun reload() {
        startActivity(Intent(baseContext, MainActivity::class.java))
    }
}