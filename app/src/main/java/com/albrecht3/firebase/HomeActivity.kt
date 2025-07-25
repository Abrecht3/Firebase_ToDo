package com.albrecht3.firebase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.albrecht3.firebase.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC,
}

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Setup
        val bundle: Bundle? = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email,provider)
    }

    private fun setup(email: String?, provider: String?) {
        binding.apply {
            emailTxtV.text = email
            providerTxtV.text = provider

            logOut.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                this@HomeActivity.onBackPressed()
            }
        }
    }
}