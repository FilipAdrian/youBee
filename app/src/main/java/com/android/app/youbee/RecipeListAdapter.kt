package com.android.app.youbee

import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_item.view.*
import java.lang.Exception

class RecipeListAdapter(private val productList: List<Recipe>) :
    RecyclerView.Adapter<RecipeListAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_view
        val title: TextView = itemView.text_view_1
        val rating: TextView = itemView.text_view_2

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return ListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currenItem = productList[position]
        Picasso.get().load(currenItem.image)
            .transform(CircleTransform(50f,0f))
            .fit().into(holder.imageView, object : Callback {
            override fun onSuccess() {
                Log.d("PICASSO", "success")
            }

            override fun onError(e: Exception?) {
                e?.printStackTrace()
                holder.imageView.setImageResource(R.drawable.youbee)
            }
        })
        holder.title.text = currenItem.label
        holder.rating.text = currenItem.yield.toString()
    }
}