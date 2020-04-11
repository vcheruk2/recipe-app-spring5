package com.ravi.recipe.service;

import com.ravi.recipe.commands.IngredientCommand;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 4/11/2020 */
public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
