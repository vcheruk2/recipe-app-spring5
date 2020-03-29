package com.ravi.recipe.repositories;

import com.ravi.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 3/29/2020 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    Iterable<Recipe> findAll();
}
