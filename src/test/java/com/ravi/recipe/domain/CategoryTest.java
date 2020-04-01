package com.ravi.recipe.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    static Category category;

    @BeforeAll
    public static void setUp(){
        category = new Category();
    }

    @Test
    void getId() {
        Long newId = 4L;
        category.setId(newId);
        assertEquals(newId, category.getId());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}