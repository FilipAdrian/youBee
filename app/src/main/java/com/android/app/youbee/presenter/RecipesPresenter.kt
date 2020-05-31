package com.android.app.youbee.presenter

import android.util.Log
import com.android.app.youbee.entity.EdamamResponseModel
import com.android.app.youbee.entity.Recipe
import com.android.app.youbee.repository.RecipesRepository
import com.android.app.youbee.view.RecipeActivityView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipesPresenter(private var view: RecipeActivityView?, private val repository: RecipesRepository) :
    Callback<EdamamResponseModel> {
    private val TAG = RecipesPresenter::class.java.name

    fun loadRecipes() {
        repository.getRecipes(this)
    }

    override fun onFailure(call: Call<EdamamResponseModel>, t: Throwable) {
        view?.displayContent(ArrayList<Recipe>())
        Log.d(TAG, "${t.message}")
    }

    override fun onResponse(call: Call<EdamamResponseModel>, response: Response<EdamamResponseModel>) {
        if (!response.isSuccessful) {
            view?.displayContent(ArrayList<Recipe>())
            Log.d(TAG, response.code().toString())
            return
        }
        if (response.body() != null) {
            view?.displayContent(response.body()!!.getAllRecipes())
        }
    }

    fun onDestroy() {
        view = null
    }
}