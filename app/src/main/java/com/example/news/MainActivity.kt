package com.example.news



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Toast


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var pageNo=1
    var article=ArrayList<Article>()
    //var isLoading=false
    lateinit var aDapter:adapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
          action bar removed in themes
        */

        getnews()
         /*recycler view*/
        layoutManager=LinearLayoutManager(this)
        aDapter= adapter(article,this)
        recycler.adapter=aDapter
        recycler.layoutManager=layoutManager
        /* scroll listner*/
        recycler.addOnScrollListener(object:RecyclerView.OnScrollListener(){


            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
               /*check every instance of scrolling*/

                    //findLastCompletelyVisibleItemPostition() returns position of last fully visible view.
                    //It checks, fully visible view is the last one.
                  // Toast.makeText(this@MainActivity, "${layoutManager.findLastCompletelyVisibleItemPosition()}", Toast.LENGTH_SHORT).show()
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == article.size - 1)
                    {
                         if(pageNo<3) {
                             pageNo = pageNo + 1
                             getnews()
                         }
                       // isLoading = true
                    }

            }
        })
    }


    fun getnews(){
        val news=NewsService.newsInstance.getHeadLines("in",pageNo)    //page 1 and country india
        //In retrofit all requests are enqued in queue and then queue pocess them one by one
        //whenever one request is completed its call back will be called
        news.enqueue(object: Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news1:News?=response.body()
                if(news1!=null){
                     article.addAll(news1.articles)


                     //Log.d("PIYUSH",news1.totalResults.toString())             //tag must be capital
                  // if(pageNo>1)
                      aDapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                //findViewById<TextView>(R.id.text1).text="failed"
                Log.d("PIYUSH","failed to fetch",t)
            }
        })
    }
}