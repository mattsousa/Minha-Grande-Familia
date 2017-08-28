package br.com.mattsousa.minhagrandefamilia.activity

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import br.com.mattsousa.minhagrandefamilia.R
import br.com.mattsousa.minhagrandefamilia.dao.PersonDAO
import br.com.mattsousa.minhagrandefamilia.gof.Singleton
import br.com.mattsousa.minhagrandefamilia.model.User

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        val txwName = findViewById(R.id.splash_txw_name) as TextView
        val typeface = Typeface.createFromAsset(assets, "fonts/DancingScript-Regular.ttf")

        txwName.typeface = typeface
        Singleton.getInstance(applicationContext)

        handler.postDelayed({ startNewActivity() }, 2000)
    }

    private fun startNewActivity(){
        val intent : Intent =
                if(PersonDAO.isFirstUse()){
                    Intent(applicationContext, LoginActivity::class.java)
                } else {
                    Singleton.setUser(PersonDAO.getPersonByID(1) as User)
                    Intent(applicationContext, MainActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
