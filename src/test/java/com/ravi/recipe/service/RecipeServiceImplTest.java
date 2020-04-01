package com.ravi.recipe.service;

import com.ravi.recipe.domain.Recipe;
import com.ravi.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    static RecipeServiceImpl recipeService;

    @Mock
    static RecipeRepository recipeRepository;

    @BeforeAll
    public static void setUp() throws Exception{
        MockitoAnnotations.initMocks(new RecipeServiceImplTest());
        recipeService = new RecipeServiceImpl(recipeRepository);
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
}