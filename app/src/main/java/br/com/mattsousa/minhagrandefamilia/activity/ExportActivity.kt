package br.com.mattsousa.minhagrandefamilia.activity

import android.Manifest
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
import android.content.pm.PackageManager
import android.os.Environment
import android.os.Environment.getExternalStorageDirectory
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import java.io.FileWriter


class ExportActivity : AppCompatActivity() {

    private var btnExport : Button? = null

    private val PERMISSION = 1
    private val gson = Gson()
    private val filename = "export.mgf"
    private val string = gson.toJson(Singleton.getUser())
    private val file = File(Environment.getExternalStorageDirectory(),filename)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export)
        btnExport = findViewById(R.id.export_btn_export) as Button

        btnExport!!.setOnClickListener({
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED){
                val array : Array<String> = Array(2, {i ->
                    when(i){
                        0->Manifest.permission.WRITE_EXTERNAL_STORAGE
                        else -> Manifest.permission.READ_EXTERNAL_STORAGE
                    }
                } )
                ActivityCompat.requestPermissions(this, array, PERMISSION)
            }else{
                getFile()
            }
            if(!file.exists()){
                if(ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED){
                    val array : Array<String> = Array(2, {i ->
                        when(i){
                            0->Manifest.permission.WRITE_EXTERNAL_STORAGE
                            else -> Manifest.permission.READ_EXTERNAL_STORAGE
                        }
                    } )
                    ActivityCompat.requestPermissions(this, array, PERMISSION)
                }
            }
        })

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        when(requestCode){
            PERMISSION->{
                if (grantResults.isNotEmpty().and(grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED)){
                    getFile()
                }
                else{
                    finish()
                }
            }
        }
    }
    private fun getFile(){
        file.createNewFile()
        val writer = FileWriter(file)
        writer.write(string)
        writer.flush()
        writer.close()
        if(file.exists())
            Toast.makeText(application, R.string.export_success,
                    Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(application, R.string.export_not_success,
                    Toast.LENGTH_SHORT).show()
    }
}
