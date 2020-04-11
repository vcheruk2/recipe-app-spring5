package com.ravi.recipe.service;

import com.ravi.recipe.commands.IngredientCommand;
import com.ravi.recipe.converters.IngredientToIngredientCommand;
import com.ravi.recipe.domain.Ingredient;
import com.ravi.recipe.domain.Recipe;
import com.ravi.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    IngredientToIngredientCommand ingredientToIngredientCommand;

    IngredientServiceImpl ingredientServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientServiceImpl = new IngredientServiceImpl(ingredientToIngredientCommand, recipeRepository);
    }

    @Test
    void findByRecipeIdAndIngredientIdTrueCase() throws Exception {
        // given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        Set<Ingredient> ingredientSet = new HashSet<>();
        ingredientSet.add(ingredient1);
        ingredientSet.add(ingredient2);
        ingredientSet.add(ingredient3);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        recipe.setIngredients(ingredientSet);

        // when
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        // then
        IngredientCommand ingredientCommand = ingredientServiceImpl.findByRecipeIdAndIngredientId(1L, 1L);

        assertEquals(ingredientCommand.getId(), 3L);
        assertEquals(ingredientCommand.getRecipeId(), 2L);
        verify(recipeRepository, times(1)).findById(anyLong());
    }
}