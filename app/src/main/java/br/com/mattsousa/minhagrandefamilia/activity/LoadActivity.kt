package br.com.mattsousa.minhagrandefamilia.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.mattsousa.minhagrandefamilia.R
import br.com.mattsousa.minhagrandefamilia.dao.PersonDAO
import br.com.mattsousa.minhagrandefamilia.dao.RelativeDAO
import br.com.mattsousa.minhagrandefamilia.gof.Singleton
import br.com.mattsousa.minhagrandefamilia.model.Person
import br.com.mattsousa.minhagrandefamilia.model.User
import com.google.gson.Gson
import java.io.File

class LoadActivity : AppCompatActivity() {
    var btnApply : Button? = null

    private val PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        btnApply = findViewById(R.id.load_btn_apply) as Button

        btnApply!!.setOnClickListener({
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                val permissions : Array<String> = Array(2, {i -> when(i){
                    0->Manifest.permission.WRITE_EXTERNAL_STORAGE
                    else ->Manifest.permission.READ_EXTERNAL_STORAGE
                }})
                ActivityCompat.requestPermissions(this, permissions, 1)
            }else{
                getFile()
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        when(requestCode){
            PERMISSION-> {
                if (grantResults.isNotEmpty().and(grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED)) {
                }
                else{
                    finish()
                }
                if (grantResults.isNotEmpty().and(grantResults[1] ==
                        PackageManager.PERMISSION_GRANTED)) {
                    getFile()
                }
                else{
                    finish()
                }
            }
        }
    }

    private fun getFile(){
        val file = File(Environment.getExternalStorageDirectory(), "export.mgf")
        val gson = Gson()
        var gsonString = StringBuilder()

        for(buffer in file.readLines()){
            gsonString.append(buffer)
        }

        val user = gson.fromJson(gsonString.toString(), User::class.java) as User

        PersonDAO.insert(user, true)
        for(item in user.relatives){
            RelativeDAO.insert(item)
        }

        Singleton.setUser(user)

        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }
}
