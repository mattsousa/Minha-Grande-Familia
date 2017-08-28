package br.com.mattsousa.minhagrandefamilia.activity

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import br.com.mattsousa.minhagrandefamilia.R

class AboutActivity : AppCompatActivity() {

    var txwVersionName : TextView? = null
    var txwName : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val typeface = Typeface.createFromAsset(assets, "fonts/DancingScript-Regular.ttf")
        txwVersionName = findViewById(R.id.about_txw_namever) as TextView
        txwName = findViewById(R.id.about_txw_name) as TextView
        txwVersionName!!.typeface = typeface
        txwName!!.typeface = typeface
    }
}
