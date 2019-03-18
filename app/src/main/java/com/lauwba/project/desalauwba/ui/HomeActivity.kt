package com.lauwba.project.desalauwba.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lauwba.project.desalauwba.R
import com.lauwba.project.desalauwba.ui.lainnya.LainnyaFragment
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setSupportActionBar(toolbar)
        title = "Home"
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,HomeFragment())
            .commit()
       bottom.setOnNavigationItemSelectedListener {
           when (it.itemId){
               R.id.home -> {
                   supportFragmentManager.beginTransaction()
                           .replace(R.id.container,HomeFragment())
                           .commit()
                   return@setOnNavigationItemSelectedListener true
               }

               R.id.pengaduan -> {
                   supportFragmentManager.beginTransaction()
                           .replace(R.id.container,PengaduanFragment())
                           .commit()
                   return@setOnNavigationItemSelectedListener true
               }
               R.id.profil -> {
                   if(Prefs.contains("email")){
                       supportFragmentManager.beginTransaction()
                           .replace(R.id.container,ProfilFragment())
                           .commit()
                   }else{
                       supportFragmentManager.beginTransaction()
                           .replace(R.id.container,Login())
                           .commit()
                   }
                   return@setOnNavigationItemSelectedListener true
               }
               R.id.lainnya -> {
                   supportFragmentManager.beginTransaction()
                           .replace(R.id.container, LainnyaFragment())
                           .commit()
                   return@setOnNavigationItemSelectedListener true
               }
           }
           return@setOnNavigationItemSelectedListener false
       }
    }

}
