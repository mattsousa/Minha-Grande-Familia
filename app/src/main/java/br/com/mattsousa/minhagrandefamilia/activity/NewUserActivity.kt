package br.com.mattsousa.minhagrandefamilia.activity

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import br.com.mattsousa.minhagrandefamilia.R
import br.com.mattsousa.minhagrandefamilia.dao.PersonDAO
import br.com.mattsousa.minhagrandefamilia.gof.Singleton
import br.com.mattsousa.minhagrandefamilia.model.Person
import br.com.mattsousa.minhagrandefamilia.model.User
import java.text.DateFormat
import java.util.*

class NewUserActivity : AppCompatActivity() {
    private var txwInformation : TextView? = null
    private var edtName : EditText? = null
    private var fabNext : FloatingActionButton? = null
    private var lnlyScreen2 : LinearLayout? = null
    private var btnMale : Button? = null
    private var btnFemale : Button? = null
    private var btnBirth : Button? = null
    private var lnlyScreen3 : LinearLayout? = null
    private var edtEmail : EditText? = null
    private var edtEmailOk : EditText? = null

    private var sex : Char = 'M'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        // Bind all view's ids with their respective variables
        globalAssignments()

        fabNext!!.setOnClickListener({view ->  fabClick(view) })

        edtEmail!!.letterSpacing = .2f
        edtEmailOk!!.letterSpacing = .2f

        btnMale!!.setOnClickListener({
            sexClick(Person.SEX_MALE)
        })
        btnFemale!!.setOnClickListener({
            sexClick(Person.SEX_FEMALE)
        })

        btnBirth!!.setOnClickListener({
            birthClick()
        })
    }

    override fun onResume() {
        super.onResume()
        changeSexButtonColor()
    }

    override fun onBackPressed() {
        when {
            lnlyScreen3!!.visibility == View.VISIBLE -> {
                txwInformation!!.text = getString(R.string.nuser_gender_birthday)
                lnlyScreen2!!.visibility = View.VISIBLE
                lnlyScreen3!!.visibility = View.GONE
            }
            lnlyScreen2!!.visibility == View.VISIBLE -> {
                txwInformation!!.text = getString(R.string.nuser_name)
                lnlyScreen2!!.visibility = View.GONE
                edtName!!.visibility = View.VISIBLE
            }
            else -> super.onBackPressed()
        }
    }

    private fun globalAssignments(){
        txwInformation = findViewById(R.id.nuser_txw_information) as TextView
        edtName = findViewById(R.id.nuser_edt_name) as EditText
        fabNext = findViewById(R.id.nuser_fab_next) as FloatingActionButton
        lnlyScreen2 = findViewById(R.id.nuser_lnly_screen2) as LinearLayout
        btnMale = findViewById(R.id.nuser_btn_male) as Button
        btnFemale = findViewById(R.id.nuser_btn_female) as Button
        btnBirth = findViewById(R.id.nuser_btn_birth) as Button
        lnlyScreen3 = findViewById(R.id.nuser_lnly_screen3) as LinearLayout
        edtEmail = findViewById(R.id.nuser_edt_email) as EditText
        edtEmailOk = findViewById(R.id.nuser_edt_email_ok) as EditText
    }

    private fun fabClick(view: View){
        if(lnlyScreen3!!.visibility == View.VISIBLE){
            if(edtEmail!!.text.toString() == edtEmailOk!!.text.toString() ){
                if(edtEmail!!.text.isBlank()){
                    Snackbar.make(view, R.string.nuser_empty_email,Snackbar.LENGTH_SHORT ).show()
                }
                else{
                    saveUser()
                    changeScreen()
                }
            }else{
                Snackbar.make(view, R.string.nuser_error_email,Snackbar.LENGTH_SHORT ).show()
            }
        }else if(lnlyScreen2!!.visibility == View.VISIBLE){
            txwInformation!!.text = getString(R.string.nuser_email_nd_confirm)
            lnlyScreen2!!.visibility = View.GONE
            lnlyScreen3!!.visibility = View.VISIBLE
        }else if(edtEmail!!.visibility == View.VISIBLE){
            if(!edtName!!.text.toString().isBlank()){
                txwInformation!!.text = getString(R.string.nuser_gender_birthday)
                lnlyScreen2!!.visibility = View.VISIBLE
                edtName!!.visibility = View.GONE
            }
            else{
                Snackbar.make(view, R.string.nuser_empty_name,Snackbar.LENGTH_SHORT ).show()
            }
        }
    }

    private fun sexClick(sex : Char){
        this.sex = sex
        changeSexButtonColor()
    }

    private fun birthClick(){
        val builder = AlertDialog.Builder(this)
        val datePicker = DatePicker(applicationContext)
        builder.setView(datePicker)
        builder.setTitle(R.string.global_birthday_dialog)
        builder.setPositiveButton(R.string.global_ok, { _, _ -> // Parameters not in use
            val cal = Calendar.getInstance()
            cal.set(datePicker.year, datePicker.month, datePicker.dayOfMonth)
            btnBirth!!.text = DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault())
                    .format(cal.time)
        })
        builder.setNegativeButton(R.string.global_cancel, {dialog, id ->})
        builder.show()
    }

    private fun changeSexButtonColor(){
        when(sex){
            Person.SEX_MALE -> {
                btnMale!!.setBackgroundColor(Color.BLUE)
                btnMale!!.setTextColor(Color.WHITE)
                btnFemale!!.setBackgroundColor(Color.WHITE)
                btnFemale!!.setTextColor(Color.BLACK)
            }
            Person.SEX_FEMALE -> {
                btnFemale!!.setBackgroundColor(Color.RED)
                btnFemale!!.setTextColor(Color.WHITE)
                btnMale!!.setBackgroundColor(Color.WHITE)
                btnMale!!.setTextColor(Color.BLACK)
            }
        }
    }

    private fun saveUser(){
        val user = User(sex, edtName!!.text.toString(),btnBirth!!.text.toString())
        Singleton.setUser(user)
        PersonDAO.insert(applicationContext, user, true)
    }

    private fun changeScreen(){
        val intent = Intent(applicationContext, MainActivity::class.java)
        val handler = Handler()
        val wellcomeMessage = StringBuilder()
        wellcomeMessage.append(getString(R.string.nuser_wellcome))
        wellcomeMessage.append(" ")
        when(sex){

            'M'->wellcomeMessage.append(getString(R.string.global_treatment_male))
            'F'->wellcomeMessage.append(getString(R.string.global_treatment_female))
        }
        wellcomeMessage.append(edtName!!.text.toString())
        txwInformation!!.text = wellcomeMessage
        lnlyScreen3!!.visibility = View.GONE
        handler.postDelayed({
            startActivity(intent)
            finish()
        }, 1000)
    }
}
