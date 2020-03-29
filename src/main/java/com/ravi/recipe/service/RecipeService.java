package com.ravi.recipe.service;

import com.ravi.recipe.domain.Recipe;

import java.util.Set;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 3/29/2020 */
public interface RecipeService {
    Set<Recipe> getRecipes();
}
