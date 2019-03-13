package com.lauwba.project.desalauwba.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lauwba.project.desalauwba.R
import com.lauwba.project.desalauwba.ui.berita.Berita
import com.lauwba.project.desalauwba.ui.galeri.Galeri
import com.lauwba.project.desalauwba.ui.layanan.Layanan
import com.lauwba.project.desalauwba.ui.pengumuman.Pengumuman
import com.lauwba.project.desalauwba.ui.potensidesa.PotensiDesa
import com.lauwba.project.desalauwba.ui.programdesa.ProgramDesa
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    companion object {
        val TAG: String = HomeFragment::class.java.simpleName
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_home)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layanan.setOnClickListener { startActivity(Intent(activity, Layanan::class.java)) }
        berita.setOnClickListener { startActivity(Intent(activity, Berita::class.java)) }
        pengumuman.setOnClickListener { startActivity(Intent(activity,
            Pengumuman::class.java)) }
        galeri.setOnClickListener { startActivity(Intent(activity, Galeri::class.java)) }
        potensi.setOnClickListener { startActivity(Intent(activity,
            PotensiDesa::class.java)) }
        program.setOnClickListener { startActivity(Intent(activity,
            ProgramDesa::class.java)) }
    }

}