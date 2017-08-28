package br.com.mattsousa.minhagrandefamilia.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import br.com.mattsousa.minhagrandefamilia.R
import br.com.mattsousa.minhagrandefamilia.gof.Singleton
import com.google.gson.Gson
import java.io.File
import java.io.FileOutputStream
import android.content.Context.MODE_PRIVATE
import android.os.Environment
import android.os.Environment.getExternalStorageDirectory
import android.util.Log
import java.io.FileWriter


class ExportActivity : AppCompatActivity() {

    private var btnExport : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export)
        btnExport = findViewById(R.id.export_btn_export) as Button

        btnExport!!.setOnClickListener({
            val gson = Gson()
            val filename = "export.mgf"
            val string = gson.toJson(Singleton.getUser())
            val file = File(Environment.getExternalStorageDirectory(),filename)
            if(!file.exists()){
                file.createNewFile()
            }
            val writer = FileWriter(file)
            writer.write(string)
            writer.flush()
            writer.close()
            if(file.exists())
                Toast.makeText(application, R.string.export_success,Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(application, R.string.export_not_success,Toast.LENGTH_SHORT).show()

        })

    }
}
