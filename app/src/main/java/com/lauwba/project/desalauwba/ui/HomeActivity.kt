package com.lauwba.project.desalauwba.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lauwba.project.desalauwba.R
import com.lauwba.project.desalauwba.ui.lainnya.LainnyaFragment

import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
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
                   supportFragmentManager.beginTransaction()
                           .replace(R.id.container,ProfilFragment())
                           .commit()
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
