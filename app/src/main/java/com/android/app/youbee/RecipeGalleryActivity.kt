package com.android.app.youbee

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_gallery.*

class RecipeGalleryActivity : AppCompatActivity() {
    private val TAG = "RecipeGalleryActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_gallery)
        getIncomingIntent()
    }

    private fun getIncomingIntent() {
        d(TAG, "getIncomingIntent: check")
        if (intent.hasExtra("url") &&
            intent.hasExtra("image") &&
            intent.hasExtra("label") &&
            intent.hasExtra("yield") &&
            intent.hasExtra("ingr")
        ) {
            d(TAG, "getIncomingIntent: found extras")
            val url = intent.getStringExtra("url")
            val image = intent.getStringExtra("image")
            val label = intent.getStringExtra("label")
            val ingr = intent.getStringExtra("ingr")
            val yield = intent.getStringExtra("yield")
            setCoontent(url, image, label, ingr, `yield`)
        }
    }

    private fun setCoontent(
        url: String,
        image: String,
        label: String,
        ingr: String,
        yield: String
    ) {

        Picasso.get().load(image).transform(CircleTransform(150f, 0f)).fit()
            .into(recipeImg, object : Callback {
                override fun onSuccess() {
                    d(TAG, "image was download")
                }

                override fun onError(e: Exception?) {
                    d(TAG, e!!.message)
                    recipeImg.setImageResource(R.mipmap.ic_no_connection)
                }
            })

        recipeTitleTxt.text = label
        yieldTxt.text = "Yield: " + `yield`
        ingredientsTxt.text = ingr
        linkTxt.text = "Link: " + url
    }
}