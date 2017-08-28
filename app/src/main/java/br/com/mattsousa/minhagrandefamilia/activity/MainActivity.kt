package br.com.mattsousa.minhagrandefamilia.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import br.com.mattsousa.minhagrandefamilia.MainAdapter
import br.com.mattsousa.minhagrandefamilia.R
import br.com.mattsousa.minhagrandefamilia.dao.RelativeDAO
import br.com.mattsousa.minhagrandefamilia.gof.Singleton
import br.com.mattsousa.minhagrandefamilia.model.Relative
import br.com.mattsousa.minhagrandefamilia.model.User

class MainActivity : AppCompatActivity() {
    private var cdvwAdd : CardView? = null
    private var cdvwTree : CardView? = null
    private var rcvwRelatives : RecyclerView? = null

    private var listRelatives : ArrayList<Relative>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        globalAssignments()


        cdvwAdd!!.setOnClickListener({addClick()})
        cdvwTree!!.setOnClickListener({treeClick()})

        rcvwRelatives!!.setHasFixedSize(true)

        rcvwRelatives!!.layoutManager = LinearLayoutManager(this)

        Snackbar.make(findViewById(R.id.main_ctly_layout),
                R.string.main_remind_birthday, Snackbar.LENGTH_SHORT)
                .show()
    }

    override fun onResume() {
        super.onResume()
        if (RelativeDAO.nTuples() == 0){
            cdvwTree!!.visibility = View.GONE
        }
        else{
            cdvwTree!!.visibility = View.VISIBLE
            updateList()
        }
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
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            R.id.action_birthday -> {
                startActivity(Intent(this, BirthdayActivity::class.java))
                true
            }
            R.id.action_export -> {
                startActivity(Intent(this, ExportActivity::class.java))
                true
            }
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

    private fun updateList(){
        listRelatives = RelativeDAO.getRelatives()
        Singleton.getUser().relatives = listRelatives
        rcvwRelatives!!.adapter = MainAdapter(listRelatives, this)
        rcvwRelatives!!.adapter.notifyDataSetChanged()
    }
}
