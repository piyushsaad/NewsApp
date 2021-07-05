package com.example.news

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class adapter(var todo:ArrayList<Article>,val context:Context): RecyclerView.Adapter<adapter.TodoViewHolder>() {
    inner class TodoViewHolder(item: View): RecyclerView.ViewHolder(item)


    //RecyclerView calls this method whenever it needs to create a new ViewHolder.
    // The method creates and initializes the ViewHolder and its associated View,
    // but does not fill in the view's contents—the ViewHolder has not yet been bound to specific data.

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return TodoViewHolder(view)
    } // false cause we dont want to attach view to root which is parent



    // RecyclerView calls this method to associate a ViewHolder with data.
    // The method fetches the appropriate data and uses the data to fill in the view holder's layout.
    // For example, if the RecyclerView dislays a list of names, the method might find
    // the appropriate name in the list and fill in the view holder's TextView widget.

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.textView).text=todo[position].title
            findViewById<TextView>(R.id.textView2).text=todo[position].description

        }
        /*
        Glide.with(fragment)
            .load(url)
            .into(imageView);*/

        Glide.with(context)
            .load(todo[position].urlToImage)
            .into(holder.itemView.findViewById<ImageView>(R.id.imageView))

        /* on click on all items*/
        holder.itemView.setOnClickListener{
            Intent(context,webView::class.java).also {
                it.putExtra("URL",todo[position].url)
                 context.startActivity(it)
            }

        }

    }
    //RecyclerView calls this method to get the size of the data set.
    // For example, in an address book app, this might be the total number of addresses.
    // RecyclerView uses this to determine when there are no more items that can be displayed.
    override fun getItemCount(): Int {
        return todo.size
    }

}