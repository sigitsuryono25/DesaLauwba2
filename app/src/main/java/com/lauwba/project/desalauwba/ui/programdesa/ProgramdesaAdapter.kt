package com.lauwba.project.desalauwba.ui.programdesa

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
import com.lauwba.project.desalauwba.ui.layanan.LayananModel
import com.lauwba.project.desalauwba.ui.potensidesa.PotensidesaAdapter

class ProgramdesaAdapter (private var context: Context, private var list:MutableList<ProgramdesaModel>): RecyclerView.Adapter<ProgramdesaAdapter.ViewHolder>() {
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item=list.get(p1)
        p0.judul.text=item.lokasi
        p0.judul.setOnClickListener {
            val i= Intent(context, DetailPengumuman::class.java)
            i.putExtra("id",item.id_program)
            i.putExtra("from","Layanan")
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


