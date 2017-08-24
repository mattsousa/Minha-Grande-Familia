package br.com.mattsousa.minhagrandefamilia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {

    val btnCreate : Button = findViewById(R.id.login_btn_create) as Button
    val btnLogin : Button = findViewById(R.id.login_btn_login) as Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // btnCreate.setOnClickListener({ changeScreen(NewUserActivity::class.java) })
        // btnLogin.setOnClickListener({ changeScreen(LoginActivity::class.java) })
    }

    private fun changeScreen(java: Class<SplashActivity>) {
        val intent = Intent(applicationContext, java)
        startActivity(intent)
    }
}
