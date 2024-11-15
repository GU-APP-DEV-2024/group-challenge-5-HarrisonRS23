package com.zybooks.workingwithdata

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class Rover : AppCompatActivity() {
    lateinit var startDateTextView: TextView
    lateinit var startDateEditText: EditText
    lateinit var endDateTextView: TextView
    lateinit var endDateEditText: EditText
    lateinit var recyclerView: RecyclerView
    lateinit var imageDataSetRover: ArrayList<ImageData>
    lateinit var imageCustomAdapter: ImageCustomAdapterRover
    lateinit var countEditText: EditText

    data class ImageData(val url: String, val description: String = "", val date: String = "") {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rover)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.title = "Working with NASA"


        imageDataSetRover = arrayListOf(
            ImageData("https://mars.nasa.gov/mars2020-raw-images/pub/ods/surface/sol/01327/ids/edr/browse/ncam/NLF_1327_0784745600_237ECM_N0620064NCAM03327_07_195J01_800.jpg", ""),
            ImageData("https://mars.nasa.gov/mars2020-raw-images/pub/ods/surface/sol/01327/ids/edr/browse/ncam/NRF_1327_0784745600_237ECM_N0620064NCAM03327_10_195J01_800.jpg", ""),
            ImageData("https://mars.nasa.gov/mars2020-raw-images/pub/ods/surface/sol/01327/ids/edr/browse/ncam/NRF_1327_0784745841_854ECM_N0620064NCAM13327_01_195J01_800.jpg", ""),
            ImageData("https://mars.nasa.gov/mars2020-raw-images/pub/ods/surface/sol/01327/ids/edr/browse/ncam/NLF_1327_0784746083_722ECM_N0620064NCAM02327_01_195J01_800.jpg", ""),
            ImageData("https://mars.nasa.gov/mars2020-raw-images/pub/ods/surface/sol/01327/ids/edr/browse/ncam/NRF_1327_0784745600_237ECM_N0620064NCAM03327_10_195J01_800.jpg", ""),
            ImageData("https://mars.nasa.gov/mars2020-raw-images/pub/ods/surface/sol/01327/ids/edr/browse/ncam/NRF_1327_0784745841_854ECM_N0620064NCAM13327_01_195J01_800.jpg", ""),
            ImageData("https://mars.nasa.gov/mars2020-raw-images/pub/ods/surface/sol/01327/ids/edr/browse/ncam/NLF_1327_0784746083_722ECM_N0620064NCAM02327_01_195J01_800.jpg", ""),
            ImageData("https://mars.nasa.gov/mars2020-raw-images/pub/ods/surface/sol/01327/ids/edr/browse/ncam/NRF_1327_0784745600_237ECM_N0620064NCAM03327_10_195J01_800.jpg", ""),
            ImageData("https://mars.nasa.gov/mars2020-raw-images/pub/ods/surface/sol/01327/ids/edr/browse/ncam/NRF_1327_0784745841_854ECM_N0620064NCAM13327_01_195J01_800.jpg", ""),
            ImageData("https://mars.nasa.gov/mars2020-raw-images/pub/ods/surface/sol/01327/ids/edr/browse/ncam/NLF_1327_0784746083_722ECM_N0620064NCAM02327_01_195J01_800.jpg", "")
        )

        imageCustomAdapter = ImageCustomAdapterRover(imageDataSetRover)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = imageCustomAdapter
    }

    // Create and make request
    // Create a new JsonObjectRequest that requests available subjects


    private fun processRequest(response: JSONArray) {
        Log.d(TAG, response.toString())
        for (index in 0 .. response.length() - 1) {
            var jsonObject = response.getJSONObject(index)
            var url = jsonObject.getString("url")
            var explanation = jsonObject.getString("explanation")
            imageDataSetRover.add(ImageData(url, explanation))
        }
        imageCustomAdapter.notifyDataSetChanged()
    }


    private fun clearEditTextFields() {
        countEditText.text.clear()
        startDateEditText.text.clear()
        endDateEditText.text.clear()
    }
}