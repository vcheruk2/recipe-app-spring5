package com.ravi.recipe.service;

import com.ravi.recipe.commands.IngredientCommand;
import com.ravi.recipe.converters.IngredientToIngredientCommand;
import com.ravi.recipe.domain.Recipe;
import com.ravi.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 4/11/2020 */
@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()){
            // TODO: Yet to implement error handling
            log.error("Recipe id not found. Id: "+ recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommand = recipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getId().equals(ingredientId))
                        .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if (!ingredientCommand.isPresent()){
            // TODO: Yet to implement error handling
            log.error("Ingredient id not found. Id: "+ ingredientId);
        }

        return ingredientCommand.get();
    }
}
