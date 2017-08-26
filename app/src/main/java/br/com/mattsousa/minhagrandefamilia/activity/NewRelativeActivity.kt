package br.com.mattsousa.minhagrandefamilia.activity

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import br.com.mattsousa.minhagrandefamilia.R
import br.com.mattsousa.minhagrandefamilia.dao.RelativeDAO
import br.com.mattsousa.minhagrandefamilia.model.Kinship
import br.com.mattsousa.minhagrandefamilia.model.Person
import br.com.mattsousa.minhagrandefamilia.model.Relative
import java.text.DateFormat
import java.util.*

class NewRelativeActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener{

    private var imvwPhoto : ImageView? = null
    private var edtName : EditText? = null
    private var spnParentage : Spinner? = null
    private var btnBirthday : Button? = null
    private var btnXInfo : Button? = null
    private var btnSave : Button? = null

    private var kinship : Kinship = Kinship.ME

    private var relative : Relative? = null
    private var sex : Char? = null

    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_relative)
        globalAssignments()
        spnInsertValues()

        edtName!!.letterSpacing = .2f

        spnParentage!!.onItemSelectedListener = this

        btnSave!!.setOnClickListener({saveClick()})

        btnBirthday!!.setOnClickListener({birthClick()})

        btnXInfo!!.setOnClickListener({xinfoClick()})

        imvwPhoto!!.setOnClickListener({imageClick()})
    }

    private fun globalAssignments(){
        imvwPhoto = findViewById(R.id.nrelative_imvw_photo) as ImageView
        edtName = findViewById(R.id.nrelative_edt_name) as EditText
        spnParentage = findViewById(R.id.nrelative_spn_parentage) as Spinner
        btnBirthday = findViewById(R.id.nrelative_btn_birthday) as Button
        btnXInfo = findViewById(R.id.nrelative_btn_xinfo) as Button
        btnSave = findViewById(R.id.nrelative_btn_save) as Button
    }

    private fun saveClick(){
        val nameBlank = edtName!!.text.isBlank()
        val btnNotBirthday = btnBirthday!!.text == getString(R.string.nrelatives_birthday_ex)
        if(nameBlank.or(btnNotBirthday)){
           if(nameBlank){
               Toast.makeText(applicationContext,
                       R.string.nuser_empty_name, Toast.LENGTH_SHORT).show()
           }
            if(btnNotBirthday){
                Toast.makeText(applicationContext,
                        R.string.nuser_empty_birthday, Toast.LENGTH_SHORT).show()
            }
        }else{
            relative = Relative(sex!!, edtName!!.text.toString(),
                    btnBirthday!!.text.toString(),kinship)
            RelativeDAO.insert(applicationContext, relative)
            onBackPressed()
        }
    }

    private fun spnInsertValues() {
        val adapter : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
                applicationContext,R.array.nrelative_kinship_spn,
                android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnParentage!!.adapter = adapter
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val item = p0!!.getItemAtPosition(p2) as CharSequence
        when(item.toString()){
            Kinship.SON.relationName -> {
                kinship = Kinship.SON
                sex = Person.SEX_MALE
            }
            Kinship.DAUGHTER.relationName -> {
                kinship = Kinship.DAUGHTER
                sex = Person.SEX_FEMALE
            }
            Kinship.FATHER.relationName -> {
                kinship = Kinship.FATHER
                sex = Person.SEX_MALE
            }
            Kinship.MOTHER.relationName-> {
                kinship = Kinship.MOTHER
                sex = Person.SEX_FEMALE
            }
            Kinship.GRANDMOTHER.relationName-> {
                kinship = Kinship.GRANDMOTHER
                sex = Person.SEX_FEMALE
            }
            Kinship.GRANDFATHER.relationName-> {
                kinship = Kinship.GRANDFATHER
                sex = Person.SEX_MALE
            }
            Kinship.GRANDSON.relationName-> {
                kinship = Kinship.GRANDSON
                sex = Person.SEX_MALE
            }
            Kinship.GRANDDAUGHTER.relationName-> {
                kinship = Kinship.GRANDDAUGHTER
                sex = Person.SEX_FEMALE
            }
            Kinship.UNCLE.relationName-> {
                kinship = Kinship.UNCLE
                sex = Person.SEX_MALE
            }
            Kinship.AUNT.relationName-> {
                kinship = Kinship.AUNT
                sex = Person.SEX_FEMALE
            }
            Kinship.BROTHER.relationName-> {
                kinship = Kinship.BROTHER
                sex = Person.SEX_MALE
            }
            Kinship.SISTER.relationName-> {
                kinship = Kinship.SISTER
                sex = Person.SEX_FEMALE
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(applicationContext,R.string.nrelatives_no_choose ,Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){
            val extra = data!!.extras
            val image = extra.get("data") as Bitmap
            imvwPhoto!!.setImageBitmap(image)
        }
    }

    private fun birthClick(){
        val builder = AlertDialog.Builder(this)
        val datePicker = DatePicker(applicationContext)
        builder.setView(datePicker)
        builder.setTitle(R.string.global_birthday_dialog)
        builder.setPositiveButton(R.string.global_ok, { _, _ -> // Parameters not in use
            val cal = Calendar.getInstance()
            cal.set(datePicker.year, datePicker.month, datePicker.dayOfMonth)
            btnBirthday!!.text = DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault())
                    .format(cal.time)
        })
        builder.setNegativeButton(R.string.global_cancel, {_,_->})
        builder.show()
    }

    private fun xinfoClick(){
        val builder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val view = inflater.inflate(R.layout.dialog_relative_extra, null)

        val edtPhone = view.findViewById<EditText>(R.id.nrelatives_edt_phone)
        val edtEmail = view.findViewById<EditText>(R.id.nrelatives_edt_email)
        val swcMarried = view.findViewById<Switch>(R.id.nrelatives_swc_married)
        val swcLives = view.findViewById<Switch>(R.id.nrelatives_swc_lives)
        builder.setView(view)
                builder.setTitle(R.string.nrelatives_xinfo_ex)
        builder.setPositiveButton(R.string.global_ok,
                { _: DialogInterface, _: Int ->
                    if(edtEmail.text.isBlank().or(edtPhone.text.isBlank())){
                        Toast.makeText(applicationContext,
                                R.string.nrelatives_empty_fields,
                                Toast.LENGTH_LONG).show()
                        xinfoClick()
                    }else{
                        relative = Relative(sex!!,edtName!!.text.toString(),
                                btnBirthday!!.text.toString(),kinship)
                        relative!!.isMarried = swcMarried.isChecked
                        relative!!.isLivesUser = swcLives.isChecked
                    }
                })
        builder.setNegativeButton(R.string.global_cancel,
                { _: DialogInterface, _: Int ->
                })
        builder.create().show()
    }

    private fun imageClick(){
        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(takePicture.resolveActivity(packageManager) != null){
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE)
        }
    }
}
