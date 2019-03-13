package com.lauwba.project.desalauwba.ui.berita

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.lauwba.project.config.Config
import com.lauwba.project.desalauwba.R
import com.lauwba.project.desalauwba.ui.RequestHandler
import com.lauwba.project.desalauwba.ui.layanan.LayananAdapter
import com.lauwba.project.desalauwba.ui.layanan.LayananModel
import kotlinx.android.synthetic.main.activity_layanan.*
import org.json.JSONObject

class Berita : AppCompatActivity() {

    private var list:MutableList<BeritaModel>?=null
    private var pd: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_berita)
        list= mutableListOf()
        get_data_berita().execute()
    }

    inner class get_data_berita : AsyncTask<String, Void, String>(){
        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@Berita,"","Wait",true,true)
        }

        override fun doInBackground(vararg params: String?): String {

            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_berita)
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                val model= BeritaModel()
                model.id_berita=data.getInt("id_berita")
                model.judul=data.getString("judul")
                model.kategori=data.getString("kategori")
                model.isi=data.getString("isi")
                model.gambar=data.getString("gambar")
                model.tanggal=data.getString("tanggal")
                list?.add(model)
                val adapter= list?.let { BeritaAdapter(it) }
                rv.layoutManager= LinearLayoutManager(this@Berita)
                rv.adapter=adapter
            }
        }

    }
}
