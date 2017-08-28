package br.com.mattsousa.minhagrandefamilia.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.Toast
import br.com.mattsousa.minhagrandefamilia.MainAdapter
import br.com.mattsousa.minhagrandefamilia.R
import br.com.mattsousa.minhagrandefamilia.dao.RelativeDAO
import br.com.mattsousa.minhagrandefamilia.gof.Singleton
import br.com.mattsousa.minhagrandefamilia.model.Kinship

class TreeActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree)
        val btnGrandparents = findViewById(R.id.tree_btn_grandparents) as Button
        val btnParents = findViewById(R.id.tree_btn_parents) as Button
        val btnUncleAunt = findViewById(R.id.tree_btn_uncle_aunt) as Button
        val btnBrother= findViewById(R.id.tree_btn_brother) as Button
        val btnSister = findViewById(R.id.tree_btn_sister) as Button
        val btnMe = findViewById(R.id.tree_btn_me) as Button
        val btnSon = findViewById(R.id.tree_btn_son) as Button
        val btnDaugther = findViewById(R.id.tree_btn_daughter) as Button
        val btnGrandchildren = findViewById(R.id.tree_btn_grandchildren) as Button

        btnGrandparents.setOnClickListener(this)
        btnParents.setOnClickListener(this)
        btnUncleAunt.setOnClickListener(this)
        btnBrother.setOnClickListener(this)
        btnSister.setOnClickListener(this)
        btnMe.setOnClickListener(this)
        btnSon.setOnClickListener(this)
        btnDaugther.setOnClickListener(this)
        btnGrandchildren.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.tree_btn_me->Toast.makeText(applicationContext,
                    R.string.tree_me_click,
                    Toast.LENGTH_SHORT).show()

            else->{
                createDialog(p0)
            }
        }
    }

    private fun createDialog(po:View?){
        val builder = AlertDialog.Builder(this)
        val recycler = RecyclerView(applicationContext)
        val button = po as Button

        val relatives = RelativeDAO.getRelativesByKinship(getKinship(button))

        recycler.adapter = MainAdapter(relatives, this)
        recycler.layoutManager = LinearLayoutManager(applicationContext)
        builder.setTitle(button.text.toString())
        builder.setView(recycler)
        builder.show()
    }

    private fun getKinship(button: Button) : ArrayList<Kinship>{
        var kinships = ArrayList<Kinship>()
        when(button.id){
            R.id.tree_btn_me->
                kinships.add(Kinship.ME)
            R.id.tree_btn_sister->
                kinships.add(Kinship.SISTER)
            R.id.tree_btn_brother->
                kinships.add(Kinship.BROTHER)
            R.id.tree_btn_son->
                kinships.add(Kinship.SON)
            R.id.tree_btn_daughter->
                kinships.add(Kinship.DAUGHTER)
            R.id.tree_btn_grandparents->{
                kinships.add(Kinship.GRANDMOTHER)
                kinships.add(Kinship.GRANDFATHER)
            }
            R.id.tree_btn_grandchildren->{
                kinships.add(Kinship.GRANDDAUGHTER)
                kinships.add(Kinship.GRANDSON)
            }
            R.id.tree_btn_uncle_aunt->{
                kinships.add(Kinship.AUNT)
                kinships.add(Kinship.UNCLE)
            }
            R.id.tree_btn_parents->{
                kinships.add(Kinship.MOTHER)
                kinships.add(Kinship.FATHER)
            }
        }
        return kinships
    }
}
