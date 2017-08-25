package br.com.mattsousa.minhagrandefamilia.activity

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import br.com.mattsousa.minhagrandefamilia.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        val txwName = findViewById(R.id.splash_txw_name) as TextView
        val typeface = Typeface.createFromAsset(assets, "fonts/DancingScript-Regular.ttf")

        txwName.typeface = typeface

        handler.postDelayed({ startNewActivity() }, 2000)
    }

    private fun startNewActivity(){
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
