package com.lauwba.project.desalauwba.ui.galeri

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.lauwba.project.config.Config
import com.lauwba.project.desalauwba.R
import com.lauwba.project.desalauwba.ui.RequestHandler
import kotlinx.android.synthetic.main.activity_galeri.*
import kotlinx.android.synthetic.main.activity_layanan.*
import org.json.JSONObject

class Galeri : AppCompatActivity() {

    private var list: MutableList<GaleriModel>? = null
    private var pd: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeri)
        list= mutableListOf()
        getdatagaleri().execute()
    }

    inner class getdatagaleri : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {   //method from asyntask, ecxecuted in first thread before Async excecution
            super.onPreExecute()
            pd = ProgressDialog.show(this@Galeri, "", "Wait", true, true)
        }

        override fun doInBackground(vararg params: String?): String {    //method Async

            val handler = RequestHandler()
            val result = handler.sendGetRequest(Config.url_galeri)
            return result
        }

        override fun onPostExecute(result: String?) {   //method Async result
            super.onPostExecute(result)
            pd?.dismiss()
            val objek = JSONObject(result)
            val array = objek.getJSONArray("data")
            for (i in 0 until array.length()) {
                val data = array.getJSONObject(i)
                val model = GaleriModel()
                model.id_galeri = data.getString("id_galeri")
                model.keterangan = data.getString("keterangan")
                model.gambar = data.getString("gambar")
                list?.add(model)
                val adapter = list?.let { GaleriAdapter(it, this@Galeri) }
                galerirv.layoutManager = GridLayoutManager(this@Galeri, 2)
                galerirv.adapter = adapter
            }
        }

    }
}
