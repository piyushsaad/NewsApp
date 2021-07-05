package com.example.news

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BaseUrl="https://newsapi.org/"
const val UrlKey="apiKey=b8d0807764da49c781a758c38aee4525"
const val EndUrl="v2/top-headlines?apiKey=b8d0807764da49c781a758c38aee4525"
interface NewsInterface{
    @GET("v2/top-headlines?apiKey=b8d0807764da49c781a758c38aee4525")
    fun getHeadLines(@Query("country") country:String, @Query("page") page:Int): Call<News>
    //whatever country and page will be pased will be pased as a query string
    //final url  //baseurl+endurl+county+page
    //"https://newsapi.org/v2/top-headlines?apiKey=b8d0807764da49c781a758c38aee4525&country=in&page=1"
    //Retrofit turns your HTTP API into a Java interface.
}

object NewsService{                  //singelton object//this object is used to get headlines
    val newsInstance:NewsInterface
    init {
        val retrofit= Retrofit.Builder()        //retrofit object
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance=retrofit.create(NewsInterface::class.java)
    }
}