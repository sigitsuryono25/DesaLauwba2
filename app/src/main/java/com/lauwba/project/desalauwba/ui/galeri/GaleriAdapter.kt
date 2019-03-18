package com.lauwba.project.desalauwba.ui.galeri

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lauwba.project.config.Config
import com.lauwba.project.desalauwba.R

class GaleriAdapter(private var url: MutableList<GaleriModel>, private var context: Context) : RecyclerView.Adapter<GaleriAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View

        v = LayoutInflater.from(parent.context).inflate(R.layout.activity_galeri_adapter, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return url.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=url.get(position)
        Log.d("GaleriAdapter",Config.url_gambar+item.gambar)
        Glide.with(context)
            .load(Config.url_galerifoto+item.gambar)
            .into(holder.gambar)

        holder.gambar.setOnClickListener {

        }
        holder.judul.setText(item.keterangan)

    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var gambar: ImageView
        var judul: TextView

        init {
            gambar = itemView!!.findViewById(R.id.gambar)
            judul = itemView!!.findViewById(R.id.judul)

        }
    }



}

