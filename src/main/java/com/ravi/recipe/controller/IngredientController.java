package com.ravi.recipe.controller;

import com.ravi.recipe.service.IngredientService;
import com.ravi.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 4/7/2020 */
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping({"/recipe/{id}/ingredients"})
    public String getIngredients(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping({"/recipe/{recipe_id}/ingredient/{ingredient_id}/show"})
    public String showIngredients(@PathVariable String recipe_id,
                                  @PathVariable String ingredient_id,
                                  Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipe_id),
                                                                                Long.valueOf(ingredient_id)));
        return "recipe/ingredient/show";
    }
}
