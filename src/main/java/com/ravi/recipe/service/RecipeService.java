package com.ravi.recipe.service;

import com.ravi.recipe.commands.RecipeCommand;
import com.ravi.recipe.domain.Recipe;

import java.util.Set;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 3/29/2020 */
public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(Long id);
    void deleteById(Long id);
}
