package br.com.mattsousa.minhagrandefamilia.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.mattsousa.minhagrandefamilia.R
import br.com.mattsousa.minhagrandefamilia.gof.TreeAdapter
import br.com.mattsousa.minhagrandefamilia.gof.Singleton
import br.com.mattsousa.minhagrandefamilia.model.Relative
import java.util.*

class BirthdayActivity : AppCompatActivity() {
    var rcvwBirthday : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday)
        rcvwBirthday = findViewById(R.id.birthday_rcvw_birthdays) as RecyclerView
        rcvwBirthday!!.layoutManager = LinearLayoutManager(applicationContext)
        rcvwBirthday!!.adapter = TreeAdapter(getRelativesByMonth())
    }

    private fun getRelativesByMonth() : ArrayList<Relative>{
        val cal = Calendar.getInstance()
        val relatives = Singleton.getUser().relatives.filter {
            it.birthdayDate.month == cal.get(Calendar.MONTH)
        }
        return relatives as ArrayList<Relative>
    }
}
