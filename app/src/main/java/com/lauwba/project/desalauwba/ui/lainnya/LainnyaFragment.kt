package com.lauwba.project.desalauwba.ui.lainnya

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import com.lauwba.project.desalauwba.R


class LainnyaFragment : Fragment() {
    lateinit var cardprofil : CardView
    lateinit var cardlembaga : CardView
    lateinit var carddata : CardView
    lateinit var cardprivacy : CardView



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile,null);

        cardprofil.setOnClickListener { view -> Log.d("cardprofil", "Selected") }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardprofil = view.findViewById(R.id.cardprofil)
        cardlembaga = view.findViewById(R.id.cardlembaga)
        carddata = view.findViewById(R.id.carddata)
        cardprivacy=view.findViewById(R.id.cardprivacy)



    }

}