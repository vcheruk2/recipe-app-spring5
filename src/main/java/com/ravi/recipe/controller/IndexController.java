package com.ravi.recipe.controller;

import com.ravi.recipe.domain.Category;
import com.ravi.recipe.domain.UnitOfMeasure;
import com.ravi.recipe.repositories.CategoryRepository;
import com.ravi.recipe.repositories.RecipeRepository;
import com.ravi.recipe.repositories.UnitOfMeasureRepository;
import com.ravi.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Slf4j
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;
    private final RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"/", "", "/index", "/index.html"})
    public String mapIndex(Model model){

        Optional<Category> optionalCategory = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");
        //Iterable<Recipe> recipes = recipeRepository.findAll();

        System.out.println("Category ID = "+optionalCategory.get().getId());
        System.out.println("Unit of Measure ID = "+optionalUnitOfMeasure.get().getId());

        System.out.println("hi 123");
        log.debug("Calling index page");

        //model.addAttribute("recipes", recipeRepository.findAll());
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
