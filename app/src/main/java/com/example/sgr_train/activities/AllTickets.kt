package com.example.sgr_train.activities

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.sgr_train.R
import com.example.sgr_train.adapters.AllTicketsAdaptors
import com.example.sgr_train.databinding.ActivityAllTicketsBinding
import com.example.sgr_train.models.Tickets
import org.json.JSONArray
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

private lateinit var binding: ActivityAllTicketsBinding
val myTicketDetails = ArrayList<Tickets>()
class AllTickets : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllTicketsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MyAsyncTask(applicationContext).execute()
    }


    class MyAsyncTask internal constructor(context: Context) : AsyncTask<String, String, String>() {
        lateinit var con: HttpURLConnection
        lateinit var resulta: String
        val builder = Uri.Builder()
        val myRecyclerView = binding.recyclerAllTickets
        private val cont: Context = context
        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun doInBackground(vararg params: String?): String? {
            try {

                var query = builder.build().encodedQuery
                val url: String = "https://sgrtransport.000webhostapp.com/sgr/alltickets.php"
                val obj = URL(url)
                con = obj.openConnection() as HttpURLConnection
                con.setRequestMethod("GET")
                con.setRequestProperty(
                    "User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"
                )
                con.setRequestProperty("Accept-Language", "UTF-8")
                con.setDoOutput(true)
                val outputStreamWriter = OutputStreamWriter(con.getOutputStream())
                outputStreamWriter.write(query)
                outputStreamWriter.flush()
                Log.e("pass 1", "connection success ")
            } catch (e: Exception) {
                Log.e("Fail 1", e.toString())
            }
            try {
                resulta = con.inputStream.bufferedReader().readText()
                Log.e("data", resulta)
            } catch (e: java.lang.Exception) {
                Log.e("Fail 2", e.toString())
            }
            return "";
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            var json_data = JSONArray(resulta)

            for (i in 0 until json_data.length()) {
                val jsonObject = json_data.getJSONObject(i)
                val Uname = jsonObject.optString("name")
                val tktNumber = jsonObject.optString("ticket_number")
                val source = jsonObject.optString("source")
                val destination = jsonObject.optString("destination")
                myTicketDetails.add(Tickets(Uname, tktNumber, source, destination))
            }
            myRecyclerView.adapter = AllTicketsAdaptors(myTicketDetails)
            Log.e("data", json_data.toString())
        }

    }
}