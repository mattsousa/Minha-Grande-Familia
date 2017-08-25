package br.com.mattsousa.minhagrandefamilia.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import br.com.mattsousa.minhagrandefamilia.R

class NewRelativeActivity : AppCompatActivity() {
    val imvwPhoto = findViewById(R.id.nrelative_imvw_photo) as ImageView
    val edtName = findViewById(R.id.nrelative_edt_name) as EditText
    val spnParentage = findViewById(R.id.nrelative_spn_parentage) as Spinner
    val btnBirthday = findViewById(R.id.nrelative_btn_birthday) as Button
    val btnXInfo = findViewById(R.id.nrelative_btn_xinfo) as Button
    val btnSave = findViewById(R.id.nrelative_btn_save) as Button

    // val parentage : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_relative)
    }
}
