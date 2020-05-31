package com.android.app.youbee.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.app.youbee.R
import com.android.app.youbee.entity.Recipe
import com.android.app.youbee.presenter.RecipesPresenter
import com.android.app.youbee.repository.impl.RecipesRepositoryImpl
import kotlinx.android.synthetic.main.recipe_list.*

class RecipeActivity : Fragment(), RecipeActivityView {
    private val TAG = "RecipeActivity"
    lateinit var presenter: RecipesPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = RecipesPresenter(this, RecipesRepositoryImpl())
        return inflater.inflate(R.layout.recipe_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycleViewerData(ArrayList(), LinearLayoutManager(activity))
        presenter.loadRecipes()

    }

    override fun displayContent(recipes: List<Recipe>) {
        if (recipes.isNotEmpty()) {
            setRecycleViewerData(recipes, LinearLayoutManager(this@RecipeActivity.context))
        } else {
            Toast.makeText(
                this@RecipeActivity.context,
                "An error occurred while loading data",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setRecycleViewerData(recipes: List<Recipe>, manager: LinearLayoutManager) {
        recycler_view.apply {
            layoutManager = manager
            setHasFixedSize(true)
            adapter = this@RecipeActivity.context?.let {
                RecipeListAdapter(
                    it,
                    recipes
                )
            }
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}