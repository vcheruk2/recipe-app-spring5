package com.ravi.recipe.controller;

import com.ravi.recipe.commands.IngredientCommand;
import com.ravi.recipe.service.IngredientService;
import com.ravi.recipe.service.RecipeService;
import com.ravi.recipe.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 4/7/2020 */
@Controller
@Slf4j
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
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

    @GetMapping
    @RequestMapping({"/recipe/{recipe_id}/ingredient/{ingredient_id}/update"})
    public String updateIngredients(@PathVariable String recipe_id,
                                    @PathVariable String ingredient_id,
                                    Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipe_id),
                                                            Long.valueOf(ingredient_id)) );
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
}
