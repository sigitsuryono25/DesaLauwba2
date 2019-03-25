package com.lauwba.project.desalauwba.ui.lainnya

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lauwba.project.desalauwba.R
import com.lauwba.project.desalauwba.ui.Privacy.Privacy
import com.lauwba.project.desalauwba.ui.datadesa.DataDesa
import com.lauwba.project.desalauwba.ui.lembaga.Lembaga
import com.lauwba.project.desalauwba.ui.profildesa.ProfilDesa
import kotlinx.android.synthetic.main.fragment_profile.*


class LainnyaFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_profile, null)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profil.setOnClickListener {
            startActivity(Intent(activity, ProfilDesa::class.java))
        }
        lembaga.setOnClickListener {
            startActivity(Intent(activity, Lembaga::class.java))
        }
        datadesa.setOnClickListener {
            startActivity(Intent(activity, DataDesa::class.java))
        }
        privacy.setOnClickListener {
            startActivity(Intent(activity, Privacy::class.java))
        }


    }

}