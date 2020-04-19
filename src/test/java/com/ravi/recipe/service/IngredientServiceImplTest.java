package com.ravi.recipe.service;

import com.ravi.recipe.commands.IngredientCommand;
import com.ravi.recipe.converters.IngredientCommandToIngredient;
import com.ravi.recipe.converters.IngredientToIngredientCommand;
import com.ravi.recipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.ravi.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.ravi.recipe.domain.Ingredient;
import com.ravi.recipe.domain.Recipe;
import com.ravi.recipe.repositories.RecipeRepository;
import com.ravi.recipe.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientServiceImpl ingredientServiceImpl;


    public IngredientServiceImplTest(){
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientServiceImpl = new IngredientServiceImpl(ingredientToIngredientCommand,
                                                recipeRepository,
                                                ingredientCommandToIngredient,
                                                unitOfMeasureRepository);
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

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        // when
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        // then
        IngredientCommand ingredientCommand = ingredientServiceImpl.findByRecipeIdAndIngredientId(1L, 3L);

        assertEquals(3L, ingredientCommand.getId());
        assertEquals(1L, ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    void saveIngredient() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //when
        IngredientCommand savedCommand = ingredientServiceImpl.saveIngredientCommand(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    public void deleteIngredientCommand() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(2L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(2L);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);

        recipe.addIngredient(ingredient);
        recipe.addIngredient(ingredient2);
        assertEquals(2, recipe.getIngredients().size());

        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        ingredientServiceImpl.deleteIngredientCommand(ingredientCommand);
        verify(recipeRepository, times(1)).findById(anyLong());
        assertEquals(1, recipe.getIngredients().size());
    }

    @Test
    public void deleteIngredientById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(2L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(2L);
        ingredient.setRecipe(recipe);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);
        ingredient2.setRecipe(recipe);

        recipe.addIngredient(ingredient);
        recipe.addIngredient(ingredient2);
        assertEquals(2, recipe.getIngredients().size());

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        ingredientServiceImpl.deleteById(2L, 2L);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
        assertEquals(1, recipe.getIngredients().size());
    }
}