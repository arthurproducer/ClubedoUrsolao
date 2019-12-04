 package br.com.arthur.clubedoursolao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import br.com.arthur.clubedoursolao.view.list.MyProductFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

 class MainActivity : AppCompatActivity() {

     private val drawerToggle: ActionBarDrawerToggle by lazy{
         ActionBarDrawerToggle(this,
             drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
         )
     }

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Configurando o Navigation Menu
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener {menuItem ->
            selectMenuOption(menuItem)
            true
        }

         //trecho de código questionável implementação
//         if(savedInstanceState == null){
//             selectMenuOption(nav_view.menu.findItem(R.id.item_category))
//            val fragment = MyProductFragment()
//             supportFragmentManager.beginTransaction()
//                 .add(R.id.myProductFragment,fragment,MyProductFragment.TAG)
//                 .commit()
//         }


        //val navigationView = findViewById(R.id.nav_view) as NavigationView
        //val navHead = navigationView.getHeaderView(0)
        //navigationView.setNavigationItemSelectedListener(this)

    }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
             //Retorna qual menu/item foi selecionado)
             R.id.item_category -> {
                 return true
             }
             R.id.item_myProducts -> {
                 drawerLayout.openDrawer(GravityCompat.START)
                 return true
             }
             R.id.item_devolution -> {
                 return true
             }
             R.id.item_sair -> logout()
         }

         //drawerToggle.closeDrawer(GravityCompat.START)
         //return true
         return super.onOptionsItemSelected(item)
     }

     private fun selectMenuOption(menuItem: MenuItem){
         menuItem.isChecked = true
         drawerLayout.closeDrawers()
     }

     fun logout(){
         //Tratar a volta para a tela e Login
         //deslogar User(pela API talvez?)
     }

 }
