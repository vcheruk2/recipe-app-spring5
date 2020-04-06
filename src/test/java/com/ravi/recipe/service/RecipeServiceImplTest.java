package com.ravi.recipe.service;

import com.ravi.recipe.converters.RecipeCommandToRecipe;
import com.ravi.recipe.converters.RecipeToRecipeCommand;
import com.ravi.recipe.domain.Recipe;
import com.ravi.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    static RecipeServiceImpl recipeService;

    @Mock
    static RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    static RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    static RecipeRepository recipeRepository;

    @BeforeAll
    public static void setUp() throws Exception{
        MockitoAnnotations.initMocks(new RecipeServiceImplTest());
        recipeService = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);
    }

    @Test
    public void getRecipes() throws Exception{
        Recipe recipe = new Recipe();
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeSet);

        assertEquals(1,
                recipeService.getRecipes().size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipesByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setDescription("New Recipe");

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        assertNotNull(recipeService.findById(1L), "Null Recipe returned");
        assertEquals(recipe.getDescription(), recipeService.findById(1L).getDescription());
        verify(recipeRepository, times(2)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void deleteRecipeById(){
        // given
        Long id = 4L;

        // when
        recipeService.deleteById(id);

        // then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}