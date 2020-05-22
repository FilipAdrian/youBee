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
        setRecycleViewerData(ArrayList(), LinearLayoutManager(this@RecipeActivity))
        getDataFromApi()
    }

    private fun setRecycleViewerData(recipes: List<Recipe>, manager: LinearLayoutManager) {
        recycler_view.apply {
            layoutManager = manager
            setHasFixedSize(true)
            adapter = RecipeListAdapter(this@RecipeActivity,recipes)
        }
    }

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
                    setRecycleViewerData(
                        response.body()!!.getAllRecipes(),
                        LinearLayoutManager(this@RecipeActivity)
                    )
                }
            }

        })
    }

}