package com.ravi.recipe.repositories;

import com.ravi.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 3/29/2020 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
