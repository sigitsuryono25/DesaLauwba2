package com.lauwba.project.desalauwba.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lauwba.project.desalauwba.R


class ProfilFragment : Fragment() {

    companion object {
        val TAG: String = ProfilFragment::class.java.simpleName
        fun newInstance() = ProfilFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_profil)
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        return view
    }

}