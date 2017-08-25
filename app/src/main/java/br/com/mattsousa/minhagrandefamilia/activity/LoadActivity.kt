package br.com.mattsousa.minhagrandefamilia.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import br.com.mattsousa.minhagrandefamilia.R

class LoadActivity : AppCompatActivity() {

    val edtPaste = findViewById(R.id.load_edt_paste) as EditText
    val btnApply = findViewById(R.id.load_btn_apply) as Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
    }
}