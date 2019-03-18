package com.lauwba.project.desalauwba.ui

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.lauwba.project.config.Config
import com.lauwba.project.desalauwba.R
import kotlinx.android.synthetic.main.activity_detail_pengumuman.*
import org.json.JSONObject

class DetailPengumuman : AppCompatActivity() {
    private var id:String?=null
    private var pd:ProgressDialog?=null
    private var from :String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pengumuman)
        id=intent.getStringExtra(Config.id)
        from=intent.getStringExtra("from")
        Toast.makeText(this@DetailPengumuman, from, Toast.LENGTH_SHORT).show()
        if (from.equals("berita",true)){
            getdetailberita().execute()
        }else if(from.equals("Potensi")){
            getdetailpotensi().execute()
        }else if(from.equals("programdesa")){
            getdetailprogramdesa().execute()
        }
        else{
            getdetail().execute()
        }

    }

    inner class getdetailprogramdesa(): AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg params: String?): String {
            val request=RequestHandler()
            return request.sendGetRequest(Config.url_detail_programdesa+id)

        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@DetailPengumuman,"","Wait...",false,true)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            if (objek.getInt("status")==1){
                Toast.makeText(this@DetailPengumuman, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            }
            else {
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    judul.text = data.getString("judul")
                    isi.text = data.getString("isi")
                    tanggal.text = data.getString("tanggal")
                    Glide.with(this@DetailPengumuman)
                        .load(Config.url_gambar + data.getString("gambar")).into(gambarpengumuman)
                }
            }
        }

    }

    inner class getdetail :AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg params: String?): String {
            val request=RequestHandler()
            return request.sendGetRequest(Config.url_detail_pengumuman+id)

        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@DetailPengumuman,"","Wait...",false,true)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            if (objek.getInt("status")==1){
                Toast.makeText(this@DetailPengumuman, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            }
            else {
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    judul.text = data.getString("judul")
                    isi.text = data.getString("isi")
                    tanggal.text = data.getString("tanggal")
                    Glide.with(this@DetailPengumuman)
                        .load(Config.url_gambar + data.getString("gambar")).into(gambarpengumuman)
                }
            }
        }

    }
    inner class  getdetailberita : AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg params: String?): String {
            val request=RequestHandler()
            return request.sendGetRequest(Config.url_detail_berita+id)

        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@DetailPengumuman,"","Wait...",false,true)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                judul.text=data.getString("judul")
                isi.text=data.getString("isi")
                tanggal.text=data.getString("tanggal")
                Glide.with(this@DetailPengumuman)
                    .load(Config.url_gambar+data.getString("gambar")).into(gambarpengumuman)
            }
        }
    }
    inner class  getdetailpotensi : AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg params: String?): String {
            val request=RequestHandler()
            return request.sendGetRequest(Config.url_detail_potensi+id)

        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@DetailPengumuman,"","Wait...",false,true)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                judul.text=data.getString("nama_desa")
                isi.text=data.getString("keterangan")
                Glide.with(this@DetailPengumuman)
                    .load(Config.url_gambar+data.getString("gambar")).into(gambarpengumuman)
            }
        }
    }
}
