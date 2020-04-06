package com.ravi.recipe.service;

import com.ravi.recipe.commands.RecipeCommand;
import com.ravi.recipe.converters.RecipeCommandToRecipe;
import com.ravi.recipe.converters.RecipeToRecipeCommand;
import com.ravi.recipe.domain.Recipe;
import com.ravi.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 4/4/2020 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class RecipeServiceIT {
    public static final String NEW_DESC = "New Desc";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        if (recipes.iterator().hasNext())
            log.debug("There are recipes");
        else
            log.debug("There are no recipes");
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESC);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(NEW_DESC, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }
}
