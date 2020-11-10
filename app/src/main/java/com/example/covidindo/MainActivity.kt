package com.example.covidindo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidindo.adapter.AdapterProv
import com.example.covidindo.model.DataItem
import com.example.covidindo.model.ResponseProv
import com.example.covidindo.network.ApiService
import com.example.covidindo.network.RetrofitBuilder.retrofit
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private var ascending =true

    companion object {
        private lateinit var adapterProv: AdapterProv

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_provinsi.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterProv.filter.filter(newText)
                return false
            }


        })

        swipe_refresh.setOnRefreshListener {
            getNegara()
            swipe_refresh.isRefreshing = false
        }
        initializedView()
        getNegara()

    }

    private fun initializedView() {
        button_refresh.setOnClickListener {
            sequenzewithoutuinterner(ascending)
            ascending =!ascending
        }
    }

    private fun sequenzewithoutuinterner(ascending: Boolean) {
        rv_prov.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            if (ascending) {
                (layoutManager as LinearLayoutManager).reverseLayout = true
                (layoutManager as LinearLayoutManager).stackFromEnd = true
            } else {
                (layoutManager as LinearLayoutManager).reverseLayout = false
                (layoutManager as LinearLayoutManager).stackFromEnd = false

            }
            adapter = adapter
        }

    }
    private fun getNegara(){
        val api  =retrofit.create(ApiService::class.java)
        api.getAllNegara().enqueue(object :Callback<ResponseProv>{
            override fun onFailure(call: Call<ResponseProv>, t: Throwable) {
                progress_bar?.visibility =View.GONE
            }

            override fun onResponse(call: Call<ResponseProv>, response: Response<ResponseProv>
            ){
                if (response.isSuccessful){
                    rv_prov.apply {
                        setHasFixedSize(true)
                        layoutManager =LinearLayoutManager(this@MainActivity)
                        progress_bar?.visibility =View.GONE
                        adapterProv =AdapterProv(response.body()!!.data as ArrayList<DataItem>){}
                        adapter = adapterProv

                    }
                }else{
                    progress_bar?.visibility=View.GONE
                }
            }
        })
    }
}




