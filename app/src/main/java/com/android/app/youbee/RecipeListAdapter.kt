package com.android.app.youbee

import android.content.Context
import android.content.Intent
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_item.view.*

class RecipeListAdapter(private val context: Context, private val productList: List<Recipe>) :
    RecyclerView.Adapter<RecipeListAdapter.ListViewHolder>() {

    private val TAG = "RecipeListAdapter"
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_view
        val title: TextView = itemView.text_view_1
        val rating: TextView = itemView.text_view_2
        val parentLayout: CardView = itemView.parent_layout

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
        val currentItem = productList[position]
        Picasso.get().load(currentItem.image)
            .transform(CircleTransform(150f, 0f))
            .fit().into(holder.imageView, object : Callback {
                override fun onSuccess() {
                    d("TAG", "success")
                }

                override fun onError(e: Exception?) {
                    e?.printStackTrace()
                    holder.imageView.setImageResource(R.mipmap.ic_no_connection)
                }
            })

        holder.title.text = currentItem.label
        holder.rating.text = currentItem.yield.toString()
        holder.parentLayout.setOnClickListener {
            val intent = Intent(context,RecipeGalleryActivity::class.java)
            intent.putExtra("ingr",currentItem.ingredientsToString())
            intent.putExtra("image",currentItem.image)
            intent.putExtra("url",currentItem.url)
            intent.putExtra("yield",currentItem.yield.toString())
            intent.putExtra("label",currentItem.label)
            context.startActivity(intent)

        }
    }

}