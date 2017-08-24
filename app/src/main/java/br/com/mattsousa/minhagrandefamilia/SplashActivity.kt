package br.com.mattsousa.minhagrandefamilia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()

        handler.postDelayed({ startNewActivity() }, 2000)
    }

    private fun startNewActivity(){
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
    }
}
