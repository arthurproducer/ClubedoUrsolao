 package br.com.arthur.clubedoursolao

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.arthur.clubedoursolao.common.BaseActivity
import br.com.arthur.clubedoursolao.view.list.MyProductFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

 class MainActivity : BaseActivity() {

     private val drawerToggle: ActionBarDrawerToggle by lazy{
         ActionBarDrawerToggle(this,
             drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
         )
     }
     private lateinit var appBarConfig: AppBarConfiguration
     private lateinit var navController: NavController

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

         val topLevelDestinations = setOf(
             R.id.item_category,R.id.item_myProducts,R.id.item_devolution)
         appBarConfig = AppBarConfiguration(topLevelDestinations,drawerLayout)

         navController = findNavController(R.id.nav_host_fragment)
         setupActionBarWithNavController(navController,appBarConfig)
         nav_view.setupWithNavController(navController)

//        //Configurando o Navigation Menu
//        drawerLayout.addDrawerListener(drawerToggle)
//        drawerToggle.syncState()
//
//        nav_view.setNavigationItemSelectedListener {menuItem ->
//            selectMenuOption(menuItem)
//            true
//        }

        //val navigationView = findViewById(R.id.nav_view) as NavigationView
        //val navHead = navigationView.getHeaderView(0)
        //navigationView.setNavigationItemSelectedListener(this)

    }

     override fun onSupportNavigateUp(): Boolean {
         return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
             //Retorna qual menu/item foi selecionado)
             R.id.item_category -> {
                 drawerLayout.openDrawer(GravityCompat.START)
                 return true
             }
             R.id.item_myProducts -> {
                 return true
             }
             R.id.item_devolution -> {
                 return true
             }
             R.id.item_exit -> {
                 logout()
                 return true
             }
         }
//         drawerLayout.closeDrawer(GravityCompat.START)

         return super.onOptionsItemSelected(item)
     }

     private fun selectMenuOption(menuItem: MenuItem){
         menuItem.isChecked = true
         drawerLayout.closeDrawers()
     }

     override fun onBackPressed() {
         if(drawerLayout.isDrawerOpen(GravityCompat.START)){
             drawerLayout.closeDrawer(GravityCompat.START)
         } else{
             super.onBackPressed()
         }
     }

     fun logout(){
             val loginIntent = Intent(this@MainActivity, LoginActivity::class.java)
             startActivity(loginIntent)
             finish()         //Tratar a volta para a tela e Login
         //deslogar User(pela API talvez?)
     }


 }
