package br.com.mattsousa.minhagrandefamilia.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.mattsousa.minhagrandefamilia.R

class ExportActivity : AppCompatActivity() {

    val btnExport = findViewById(R.id.export_btn_export)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export)
    }
}
