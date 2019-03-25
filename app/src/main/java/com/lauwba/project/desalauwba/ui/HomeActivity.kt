package com.lauwba.project.desalauwba.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lauwba.project.desalauwba.R
import com.lauwba.project.desalauwba.ui.lainnya.LainnyaFragment
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        judul.text="Home"

        supportFragmentManager.beginTransaction()
            .replace(R.id.container,HomeFragment())
            .commit()
       bottom.setOnNavigationItemSelectedListener {
           when (it.itemId){
               R.id.home -> {
                   judul.text="Home"
                   supportFragmentManager.beginTransaction()
                           .replace(R.id.container,HomeFragment())
                           .commit()
                   return@setOnNavigationItemSelectedListener true
               }

               R.id.pengaduan -> {
                   judul.text="Pengaduan"
                   supportFragmentManager.beginTransaction()
                           .replace(R.id.container,PengaduanFragment())
                           .commit()
                   return@setOnNavigationItemSelectedListener true
               }
               R.id.profil -> {
                   judul.text="Profil"
                   if(Prefs.contains("email")){
                       supportFragmentManager.beginTransaction()
                           .replace(R.id.container,ProfilFragment())
                           .commit()
                   }else{
                       judul.text="Login"
                       supportFragmentManager.beginTransaction()
                           .replace(R.id.container,Login())
                           .commit()
                   }
                   return@setOnNavigationItemSelectedListener true
               }
               R.id.lainnya -> {
                   judul.text="Lainnya"
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
