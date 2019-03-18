package com.lauwba.project.desalauwba.ui

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lauwba.project.config.Config
import com.lauwba.project.desalauwba.R
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_notifications.*
import org.json.JSONObject

class Login : Fragment(){


    private var pd: ProgressDialog?=null
    private var params:HashMap<String,String>?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notifications,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        params= hashMapOf()
        login.setOnClickListener {  get_data_login().execute() }


    }

    inner class get_data_login : AsyncTask<String, Void, String>(){
        override fun onPreExecute() {   //method from asyntask, ecxecuted in first thread before Async excecution
            super.onPreExecute()
            pd= ProgressDialog.show(activity,"","Wait",true,true)
        }

        override fun doInBackground(vararg param: String?): String {    //method Async

            val handler= RequestHandler()
            params?.put("username",email.text.toString())
            params?.put("password",password.text.toString())

            val result= params?.let { handler.sendPostRequest(Config.url_login, it) }
            return result?:""
        }

        override fun onPostExecute(result: String?) {   //method Async result
            super.onPostExecute(result)
            pd?.dismiss()
            try{
                Log.d("result",result)
                val objek= JSONObject(result)
                if (objek.getInt("status")==1){
                    Toast.makeText(activity, "Login Gagal!", Toast.LENGTH_SHORT).show()
                }else{
                    Prefs.putString("email",email.text.toString())
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.container,ProfilFragment())
                        ?.commit()
                    Toast.makeText(activity, "Berhasil Login!", Toast.LENGTH_SHORT).show()
                }
            }catch(e:Exception){
                e.printStackTrace()
            }

        }

    }
}