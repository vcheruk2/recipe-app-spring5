package com.ravi.recipe.bootstrap;

import com.ravi.recipe.domain.*;
import com.ravi.recipe.repositories.CategoryRepository;
import com.ravi.recipe.repositories.RecipeRepository;
import com.ravi.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 3/29/2020 */
@Component
public class BootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public BootStrap(RecipeRepository recipeRepository,
                     CategoryRepository categoryRepository,
                     UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Optional<UnitOfMeasure> ounceUom = unitOfMeasureRepository.findByDescription("Ounce");
        if(!ounceUom.isPresent())
            throw new RuntimeException("Expected UOM for ounce is not found");

        Optional<UnitOfMeasure> teaSpoonUom = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaSpoonUom.isPresent())
            throw new RuntimeException("Expected UOM for teaspoon is not found");

        Optional<Category> american = categoryRepository.findByDescription("American");
        if (!american.isPresent())
            throw new RuntimeException("Expected Category American is not found");

        Optional<Category> mexican = categoryRepository.findByDescription("Mexican");
        if(!mexican.isPresent())
            throw new RuntimeException("Expected Category Mexican is not found");

        // Guacamole Recipe
        Recipe guac = new Recipe();

        Ingredient avocados = new Ingredient("Avocados", BigDecimal.valueOf(2), ounceUom.get());
        Ingredient salt = new Ingredient("Salt", BigDecimal.valueOf(0.25), teaSpoonUom.get());

        Notes guacNotes = new Notes();
        //guacNotes.setRecipe(guac);
        guacNotes.setRecipeNotes("Guac Recipe Notes");

        guac.setDescription("Perfect Guacamole");
        guac.setCookTime(10);
        guac.setPrepTime(10);
        guac.setDifficulty(Difficulty.EASY);
        guac.setDirections("Directions to make Guac");
        guac.setNotes(guacNotes);
        guac.setServings(4);
        guac.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guac.setSource("Simply Recipes");

        Set<Ingredient> guacIngredients = new HashSet<>();
        guacIngredients.add(avocados);
        guacIngredients.add(salt);
        guac.setIngredients(guacIngredients);

        Set<Category> guacCategories = new HashSet<>();
        guacCategories.add(american.get());
        guacCategories.add(mexican.get());
        guac.setCategories(guacCategories);

        recipeRepository.save(guac);

        // Chicken Taco
        Recipe chickenTaco = new Recipe();
        chickenTaco.setDirections("Making of Chicken Taco");
        chickenTaco.setDescription("Spicy Chicken Taco");
        chickenTaco.setDifficulty(Difficulty.MODERATE);
        chickenTaco.setSource("Simply Recipes");
        chickenTaco.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chickenTaco.setServings(6);
        chickenTaco.setCookTime(15);
        chickenTaco.setPrepTime(20);

        Notes chickenTacoNotes = new Notes();
        chickenTaco.setNotes(chickenTacoNotes);
        chickenTacoNotes.setRecipeNotes("Chicken Taco Recipe Notes");
        //chickenTacoNotes.setRecipe(chickenTaco);

        Ingredient oil = new Ingredient("Chicken Taco Oil", BigDecimal.valueOf(2),
                            teaSpoonUom.get() );

        Ingredient chicken = new Ingredient("Chicken", BigDecimal.valueOf(6),
                                ounceUom.get());

        HashSet<Ingredient> chickenTacoIngredients = new HashSet<>();
        chickenTacoIngredients.add(oil);
        chickenTacoIngredients.add(chicken);

        chickenTaco.setIngredients(chickenTacoIngredients);

        HashSet<Category> chickenTacoCategories = new HashSet<>();
        chickenTacoCategories.add(american.get());
        chickenTacoCategories.add(mexican.get());
        chickenTaco.setCategories(chickenTacoCategories);

        recipeRepository.save(chickenTaco);
    }
}
