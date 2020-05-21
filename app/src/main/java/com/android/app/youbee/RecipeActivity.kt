package com.android.app.youbee

import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.recipe_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_list)
        getDataFromApi()
//        d(this.localClassName,recipeList.toString())

//        recycler_view.adapter = RecipeListAdapter(recipeList)
//        recycler_view.layoutManager = LinearLayoutManager(this)
//        recycler_view.setHasFixedSize(true)
    }

//    private fun generateDataList(size: Int): List<Recipe> {
//        val list = ArrayList<Recipe>()
//        for (i in 0 until size) {
//            val imageUrl = "https://www.edamam.com/web-img/69e/69e0df7cfa82cc2a10113c1c36cb2d0d.jpg"
//            val item = Recipe(imageUrl, "Item $i", Random.nextFloat())
//            list += item
//        }
//        return list
//    }

    private fun getDataFromApi() {
        val request = ServiceBuilder.buildService(EdamamEndpoint::class.java)
        val call = request.getRecipes("75da0e15b97bc087c67e6abfb3cf275f", "74ac0216")
        call.enqueue(object : Callback<EdamamResponseModel> {
            override fun onFailure(call: Call<EdamamResponseModel>, t: Throwable) {
                Toast.makeText(this@RecipeActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                d("ApiError", t.message)
            }

            override fun onResponse(
                call: Call<EdamamResponseModel>,
                response: Response<EdamamResponseModel>
            ) {
                if (!response.isSuccessful) {
                    d("ApiError", response.code().toString())
                    return
                }
                if (response.body() != null) {
//                    d(this.javaClass.name,response.body()!!.getAllRecipes().toString())
                    val recipeList = response.body()!!.getAllRecipes()
                    recycler_view.adapter = RecipeListAdapter(recipeList)
                    recycler_view.layoutManager = LinearLayoutManager(this@RecipeActivity)
                    recycler_view.setHasFixedSize(true)
                }
            }

        })
    }
}