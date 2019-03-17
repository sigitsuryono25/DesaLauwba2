package com.lauwba.project.desalauwba.ui.pengumuman
import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.lauwba.project.config.Config
import com.lauwba.project.desalauwba.R
import com.lauwba.project.desalauwba.ui.RequestHandler
import kotlinx.android.synthetic.main.activity_pengumuman.*
import org.json.JSONObject

class Pengumuman : AppCompatActivity() {

    private var list:MutableList<PengumumanModel>?=null
    private var pd:ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman)
        list= mutableListOf()
        get_data_pengumuman().execute()
    }

    inner class get_data_pengumuman : AsyncTask<String,Void,String>(){
        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@Pengumuman,"","Wait",true,true)
        }

        override fun doInBackground(vararg params: String?): String {

            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_pengumuman)
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            pd?.dismiss()
            val objek=JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                val model= PengumumanModel()
                model.id=data.getString("id_pengumuman")
                model.gambar=data.getString("gambar")
                model.judul=data.getString("judul")
                model.tanggal=data.getString("tanggal")
                list?.add(model)
                val adapter= list?.let {
                    AdapterPengumuman(
                        it,
                        this@Pengumuman
                    )
                }
                rc.layoutManager=LinearLayoutManager(this@Pengumuman)
                rc.adapter=adapter
            }
        }

    }
}
