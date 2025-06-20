package com.albrecht3.firebase

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.credentials.provider.CreateEntry
import com.albrecht3.firebase.databinding.ActivityAuthBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth


class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        enableEdgeToEdge()
        setContentView(view)

        binding.apply {

        }

        //Analytics Event
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","integracion de firebase completa")
        analytics.logEvent("InitScreen", bundle)

        setup()
    }

    private fun setup() {
        title = "Autenticacion"

        binding.apply {
            register.setOnClickListener {
                if (email.text.isNotEmpty() && password.text.isNotEmpty()){
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnCompleteListener {
                            if (it.isSuccessful){
                                showHome(it.result?.user?.email ?:"", ProviderType.BASIC)
                            }else{
                                showAlert()
                            }
                        }
                }
            }
            login.setOnClickListener {
                if (email.text.isNotEmpty() && password.text.isNotEmpty()){
                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnCompleteListener {
                            if (it.isSuccessful){
                                showHome(it.result?.user?.email ?:"", ProviderType.BASIC)
                            }else{
                                showAlert()
                            }
                        }
                }
            }
        }
    }

    private fun showHome(email: String, provider: ProviderType) {
        val homeIntent: Intent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email",email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
     }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error de autenticacion")
        builder.setPositiveButton ("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}