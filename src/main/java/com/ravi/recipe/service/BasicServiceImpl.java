package com.ravi.recipe.service;

import org.springframework.stereotype.Service;

@Service
public class BasicServiceImpl implements BasicService {

    @Override
    public String getRecipe() {
        return "Basic Recipe";
    }
}
