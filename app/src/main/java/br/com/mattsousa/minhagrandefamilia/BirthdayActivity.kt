package br.com.mattsousa.minhagrandefamilia

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class BirthdayActivity : AppCompatActivity() {
    val rcvwBirthday = findViewById(R.id.birthday_rcvw_birthdays)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday)
    }
}
