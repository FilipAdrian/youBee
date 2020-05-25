package com.android.app.youbee

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.recipe_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeActivity : Fragment() {
    private val TAG = "RecipeActivity"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recipe_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycleViewerData(ArrayList(), LinearLayoutManager(activity))
        getDataFromApi()

    }

    private fun setRecycleViewerData(recipes: List<Recipe>, manager: LinearLayoutManager) {
        recycler_view.apply {
            layoutManager = manager
            setHasFixedSize(true)
            adapter = this@RecipeActivity.context?.let { RecipeListAdapter(it, recipes) }
        }
    }

    private fun getDataFromApi() {
        val request = ServiceBuilder.buildService(EdamamEndpoint::class.java, Constant.EDAMAM_BASE_URL)
        val call = request.getRecipes(Constant.EDAMAM_API_KEY, Constant.EDAMAM_API_ID)
        call.enqueue(object : Callback<EdamamResponseModel> {
            override fun onFailure(call: Call<EdamamResponseModel>, t: Throwable) {
                Toast.makeText(this@RecipeActivity.context, "${t.message}", Toast.LENGTH_SHORT)
                    .show()
                d(TAG,"${t.message}")
            }

            override fun onResponse(
                call: Call<EdamamResponseModel>,
                response: Response<EdamamResponseModel>
            ) {
                if (!response.isSuccessful) {
                    d(TAG, response.code().toString())
                    return
                }
                if (response.body() != null) {
                    setRecycleViewerData(
                        response.body()!!.getAllRecipes(),
                        LinearLayoutManager(this@RecipeActivity.context)
                    )
                }
            }

        })
    }

}