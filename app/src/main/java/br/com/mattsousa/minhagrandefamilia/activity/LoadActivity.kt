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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        btnApply = findViewById(R.id.load_btn_apply) as Button

        btnApply!!.setOnClickListener({
            val file = File(Environment.getExternalStorageDirectory(), "export.mgf")

            val gson = Gson()
            var gsonString = StringBuilder()

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    val permissions : Array<String> = Array(1, {i -> when(i){
                        0->Manifest.permission.READ_CONTACTS
                        else ->""
                    }})
                    ActivityCompat.requestPermissions(this, permissions,
                            1)

                }
            }

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
        })
    }
}
