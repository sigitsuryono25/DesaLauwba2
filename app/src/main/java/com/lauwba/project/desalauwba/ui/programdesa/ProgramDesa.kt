package com.lauwba.project.desalauwba.ui.programdesa
import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.lauwba.project.config.Config
import com.lauwba.project.desalauwba.R
import com.lauwba.project.desalauwba.ui.RequestHandler
import kotlinx.android.synthetic.main.activity_program_desa.*
import org.json.JSONObject

class ProgramDesa : AppCompatActivity() {

    private var list:MutableList<ProgramdesaModel>?=null
    private var pd: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program_desa)
        list= mutableListOf()
        get_data_programdesa().execute()
    }

    inner class get_data_programdesa : AsyncTask<String, Void, String>(){
        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@ProgramDesa,"","Wait",true,true)
        }

        override fun doInBackground(vararg params: String?): String {

            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_programdesa)
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                val model= ProgramdesaModel()
                model.id_program=data.getInt("id_program")
                model.lokasi=data.getString("lokasi")
                model.pekerjaan=data.getString("pekerjaan")
                model.keterangan=data.getString("keterangan")
                list?.add(model)
                try {
                    val adapter= list?.let { ProgramdesaAdapter(this@ProgramDesa,it) }
                    rv.layoutManager= LinearLayoutManager(this@ProgramDesa)
                    rv.adapter=adapter

                }catch(e:Exception){
                    e.printStackTrace()
                }
            }
        }

    }
}
