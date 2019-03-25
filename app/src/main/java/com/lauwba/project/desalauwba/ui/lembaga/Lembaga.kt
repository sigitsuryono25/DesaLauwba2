package com.lauwba.project.desalauwba.ui.lembaga

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.lauwba.project.config.Config
import com.lauwba.project.desalauwba.R
import com.lauwba.project.desalauwba.ui.RequestHandler
import kotlinx.android.synthetic.main.activity_lembaga.*
import org.json.JSONObject

class Lembaga : AppCompatActivity() {

    private var list:MutableList<LembagaModel>?=null
    private var pd: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lembaga)
        list= mutableListOf()
        get_data_lembaga().execute()
    }

    inner class get_data_lembaga : AsyncTask<String, Void, String>(){

        override fun onPreExecute() {   //method from asyntask, ecxecuted in first thread before Async excecution
            super.onPreExecute()
            pd= ProgressDialog.show(this@Lembaga,"","Wait",true,true)
        }

        override fun doInBackground(vararg params: String?): String {    //method Async

            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_lembaga)
            return result
        }

        override fun onPostExecute(result: String?) {   //method Async result
            super.onPostExecute(result)
            pd?.dismiss()
            try{
                val objek= JSONObject(result)
                val array=objek.getJSONArray("data")
                for (i in 0 until array.length()){
                    val data=array.getJSONObject(i)
                    val model= LembagaModel()
                    model.id_lembaga=data.getString("id_lembaga")
                    model.nama_lembaga=data.getString("nama_lembaga")
                    model.detail_lembaga=data.getString("detail_lembaga")
                    list?.add(model)
                    val adapter= list?.let { LembagaAdapter(this@Lembaga, it) }
                    rv.layoutManager= LinearLayoutManager(this@Lembaga)
                    rv.adapter=adapter
                }
            }catch (e:Exception){
                e.printStackTrace()
            }

        }

    }
}
