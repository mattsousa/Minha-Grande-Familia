package br.com.mattsousa.minhagrandefamilia.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import br.com.mattsousa.minhagrandefamilia.R
import br.com.mattsousa.minhagrandefamilia.dao.PersonDAO
import br.com.mattsousa.minhagrandefamilia.dao.RelativeDAO
import br.com.mattsousa.minhagrandefamilia.gof.Singleton
import br.com.mattsousa.minhagrandefamilia.model.Relative
import br.com.mattsousa.minhagrandefamilia.model.User

class MainActivity : AppCompatActivity() {
    var cdvwAdd : CardView? = null
    var cdvwTree : CardView? = null
    var rcvwRelatives : RecyclerView? = null

    var listRelatives : ArrayList<Relative>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        globalAssignments()

        cdvwAdd!!.setOnClickListener({addClick()})
        cdvwTree!!.setOnClickListener({treeClick()})

        if (PersonDAO.nTuples() <= 1){
            cdvwTree!!.visibility = View.GONE
        }else{
            listRelatives = RelativeDAO.getRelatives(applicationContext)
            Singleton.getUser().relatives = listRelatives
        }

    }

    override fun onResume() {
        super.onResume()
        listRelatives = RelativeDAO.getRelatives(applicationContext)
        Singleton.getUser().relatives = listRelatives
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        return when (id) {
            R.id.action_about -> true
            R.id.action_export -> true
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun globalAssignments(){
        cdvwAdd = findViewById(R.id.main_cdvw_add) as CardView
        cdvwTree = findViewById(R.id.main_cdvw_tree) as CardView
        rcvwRelatives = findViewById(R.id.main_rcvw_relatives) as RecyclerView
    }

    private fun addClick(){
        val intent = Intent(applicationContext, NewRelativeActivity::class.java)
        startActivity(intent)
    }

    private fun treeClick(){
        val intent = Intent(applicationContext, TreeActivity::class.java)
        startActivity(intent)
    }
}
