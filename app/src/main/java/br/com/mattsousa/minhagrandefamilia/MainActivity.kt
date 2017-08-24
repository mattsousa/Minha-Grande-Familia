package br.com.mattsousa.minhagrandefamilia

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    val cdvwAdd = findViewById(R.id.main_cdvw_add) as CardView
    val cdvwTree = findViewById(R.id.main_cdvw_tree) as CardView
    val rcvwRelatives = findViewById(R.id.main_rcvw_relatives) as RecyclerView

    // var listRelatives = ArrayList<Relatives>
    // var user = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        /*
        * if (listRelatives.isEmpty()){
        *   cdvwTree.visibility = View.GONE
        * }
        * */
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return when (id) {
            R.id.action_about -> true
            R.id.action_export -> true
            else -> super.onOptionsItemSelected(item)
        }

    }
}
