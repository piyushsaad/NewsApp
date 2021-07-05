package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*

class webView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)


        val url=intent.getStringExtra("URL")
        /*we can set user agent ##header## to any mobile so that server will know user browsing on mobile */
        webView.settings.userAgentString="Mozilla/5.0 (Linux; U; Android 4.4.2; en-us; SCH-I535 Build/KOT49H) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"
        /*in our case it is by defaul mobile site*/
        if(url !=null){
             webView.settings.javaScriptEnabled=true  //in our page if any javascript is present then it can run
             /* for page load event  progress bar off*/
             webView.webViewClient=object:WebViewClient(){
                 override fun onPageFinished(view: WebView?, url: String?) {           //calls when page is load using url
                     super.onPageFinished(view, url)
                     progressBar6.visibility=View.GONE
                     webView.visibility=View.VISIBLE
                 }
             }
             webView.loadUrl(url)        //gives url to web view
        }

    }
}