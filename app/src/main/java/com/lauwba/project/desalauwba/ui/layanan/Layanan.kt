package com.lauwba.project.desalauwba.ui.layanan

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.lauwba.project.config.Config
import com.lauwba.project.desalauwba.R
import com.lauwba.project.desalauwba.ui.RequestHandler
import kotlinx.android.synthetic.main.activity_layanan.*
import org.json.JSONObject

class Layanan : AppCompatActivity() {

    private var list:MutableList<LayananModel>?=null
    private var pd: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layanan)
        list= mutableListOf()
        get_data_layanan().execute()
    }

    inner class get_data_layanan : AsyncTask<String, Void, String>(){
        override fun onPreExecute() {   //method from asyntask, ecxecuted in first thread before Async excecution
            super.onPreExecute()
            pd= ProgressDialog.show(this@Layanan,"","Wait",true,true)
        }

        override fun doInBackground(vararg params: String?): String {    //method Async

            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_layanan)
            return result
        }

        override fun onPostExecute(result: String?) {   //method Async result
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                val model= LayananModel()
                model.id=data.getString("id_layanan")
                model.namalayanan=data.getString("nama_layanan")
                model.isi=data.getString("isi_layanan")
                model.gambar=data.getString("gambar")
                list?.add(model)
                val adapter= list?.let { LayananAdapter(this@Layanan, it) }
                rv.layoutManager= LinearLayoutManager(this@Layanan)
                rv.adapter=adapter
            }
        }

    }
}
