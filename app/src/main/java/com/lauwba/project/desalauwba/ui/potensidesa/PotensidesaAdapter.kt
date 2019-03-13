package com.lauwba.project.desalauwba.ui.potensidesa

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lauwba.project.desalauwba.R
import com.lauwba.project.desalauwba.ui.DetailPengumuman
import com.lauwba.project.desalauwba.ui.layanan.LayananAdapter
import com.lauwba.project.desalauwba.ui.layanan.LayananModel

class PotensidesaAdapter (private var context: Context, private var list:MutableList<PotensidesaModel>): RecyclerView.Adapter<PotensidesaAdapter.ViewHolder>() {
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item=list.get(p1)
        p0.judul.text=item.nama_desa
        p0.judul.setOnClickListener {
            val i= Intent(context, DetailPengumuman::class.java)
            i.putExtra("id",item.id_potensi)
            i.putExtra("from","Potensi")
            context.startActivity(i)
        }
    }


    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): ViewHolder {
        var  v : View
        v = LayoutInflater.from(parent.context).inflate(R.layout.activity_layanan_adapter, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }



    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        var judul : TextView

        init {
            judul = itemView!!.findViewById(R.id.txtkartukeluarga)
        }

    }
}

