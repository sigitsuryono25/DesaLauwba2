package com.lauwba.project.desalauwba.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.lauwba.project.config.Config
import com.lauwba.project.desalauwba.R
import com.lauwba.project.desalauwba.ui.berita.Berita
import com.lauwba.project.desalauwba.ui.berita.BeritaModel
import com.lauwba.project.desalauwba.ui.galeri.Galeri
import com.lauwba.project.desalauwba.ui.layanan.Layanan
import com.lauwba.project.desalauwba.ui.pengumuman.Pengumuman
import com.lauwba.project.desalauwba.ui.potensidesa.PotensiDesa
import com.lauwba.project.desalauwba.ui.programdesa.ProgramDesa
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject


class HomeFragment : Fragment() {

    private var pd: ProgressDialog? = null
    private var list: MutableList<String>? = null

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
        pengumuman.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    Pengumuman::class.java
                )
            )
        }
        galeri.setOnClickListener { startActivity(Intent(activity, Galeri::class.java)) }
        potensi.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    PotensiDesa::class.java
                )
            )
        }
        program.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    ProgramDesa::class.java
                )
            )
        }
        list = mutableListOf()
        get_data_imageslider().execute()
    }

    inner class get_data_imageslider : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            pd = ProgressDialog.show(activity, "", "Wait", true, true)
        }

        override fun doInBackground(vararg params: String?): String {

            val handler = RequestHandler()
            val result = handler.sendGetRequest(Config.url_berita)
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek = JSONObject(result)
            if (objek.getInt("status") == 1) {
                Toast.makeText(activity, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            } else {
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    val model = BeritaModel()
                    model.id_berita = data.getString("id_berita")
                    model.judul = data.getString("judul")
                    model.kategori = data.getString("kategori")
                    model.isi = data.getString("isi")
                    model.gambar = Config.url_galerifoto + data.getString("gambar")
                    model.tanggal = data.getString("tanggal")
                    list?.add(model.gambar ?: "")

                }
                try {
                    setToImageSlider(list)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

        private fun setToImageSlider(list: MutableList<String>?) {
//                for(i in 0  until list?.size!!){
//                    val item=list.get(i)
            imageslider.setImageListener { position, imageView ->
                Log.d("gambar", list?.get(position).toString())
                activity?.let { Glide.with(it).load(list?.get(position)).into(imageView) }
            }
//                }
            imageslider.pageCount = list?.size ?: 0
        }

    }

}