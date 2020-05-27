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
        if (intent.hasExtra("recipe")
        ) {
            d(TAG, "getIncomingIntent: found extras")
            val recipe = intent.getParcelableExtra<Recipe>("recipe")
            d(TAG, recipe.toString())

            setContent(recipe)
        }
    }

    private fun setContent(recipe : Recipe) {

        Picasso.get().load(recipe.image).transform(CircleTransform(150f, 0f)).fit()
            .into(recipeImg, object : Callback {
                override fun onSuccess() {
                    d(TAG, "image was download")
                }

                override fun onError(e: Exception?) {
                    d(TAG, e!!.message)
                    recipeImg.setImageResource(R.mipmap.ic_no_connection)
                }
            })

        recipeTitleTxt.text = recipe.label
        yieldTxt.text = "Yield: " + recipe.yield
        ingredientsTxt.text = recipe.ingredientsToString()
        linkTxt.text = "Link: " + recipe.url
    }
}