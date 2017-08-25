package br.com.mattsousa.minhagrandefamilia.activity

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import br.com.mattsousa.minhagrandefamilia.R

class LoginActivity : AppCompatActivity() {

    private var btnCreate : Button? = null
    private var btnLogin : Button? = null
    private var txwName : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val typeface = Typeface.createFromAsset(assets, "fonts/DancingScript-Regular.ttf")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnCreate = findViewById(R.id.login_btn_create) as Button
        btnLogin  = findViewById(R.id.login_btn_login) as Button
        txwName = findViewById(R.id.login_txw_name) as TextView

        btnCreate!!.setOnClickListener({
            val intent = Intent(applicationContext, NewUserActivity::class.java)
            startActivity(intent)
            finish()
        })
        btnLogin!!.setOnClickListener({
            val intent = Intent(applicationContext, LoadActivity::class.java)
            startActivity(intent)
            finish()
        })

        txwName!!.typeface = typeface
    }

}
