package com.ulisesdiaz.parseojson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Json estatico
         */
        var request ="{\"personas\": [" +
                "{" +
                "\"nombre\": \"Ulises\" ," +
                "\"pais\": \"México\" ," +
                "\"estado\": \"Soltero\" ," +
                "\"experiencia\": 4}," +
                "{" +
                "\"nombre\": \"Ivan\" ," +
                "\"pais\": \"España\" ," +
                "\"estado\": \"Casado\" ," +
                "\"experiencia\": 2}" +
                "]" +
                "}"

        /**
         * Este mapeo es cde forma Nativa usando Kotlin
         */
        val json = JSONObject(request)
        val people = json.getJSONArray("personas")

        for (i in 0..people.length() -1){
            val name = people.getJSONObject(i).getString("nombre")
            val country = people.getJSONObject(i).getString("pais")
            val maritalStatus = people.getJSONObject(i).getString("estado")
            val experience = people.getJSONObject(i).getInt("experiencia")
            Log.d("MAPEO-NATIVO: ", name)
            Log.d("MAPEO-NATIVO: ", country)
            Log.d("MAPEO-NATIVO: ", maritalStatus)
            Log.d("MAPEO-NATIVO: ", experience.toString())
        }

        /**
         * Este mapeo es con la libreria GSON
         */
        val gson = Gson()
        val res = gson.fromJson(request, Personas::class.java)

        for (persona in res.personas!!){
            Log.d("MAPEO-GSON: ", persona.nombre)
            Log.d("MAPEO-GSON:  ", persona.pais)
            Log.d("MAPEO-GSON: ", persona.estado)
            Log.d("MAPEO-GSON: ", persona.experiencia.toString())
        }
    }
}