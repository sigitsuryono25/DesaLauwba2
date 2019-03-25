package com.lauwba.project.desalauwba.ui.lembaga

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lauwba.project.desalauwba.R
import com.lauwba.project.desalauwba.ui.DetailPengumuman

class LembagaAdapter (private var context: Context, private var list:MutableList<LembagaModel>): RecyclerView.Adapter<LembagaAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): ViewHolder {
        var  v : View
        v = LayoutInflater.from(parent.context).inflate(R.layout.activity_lembaga_adater, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=list.get(position)
        holder.judul.text=item.nama_lembaga
        holder.judul.setOnClickListener {
            val i= Intent(context, DetailPengumuman::class.java)
            i.putExtra("id",item.id_lembaga)
            i.putExtra("from","Lembaga")
            context.startActivity(i)
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        var judul : TextView

        init {
            judul = itemView!!.findViewById(R.id.txtkarangtaruna)
        }

    }


}
