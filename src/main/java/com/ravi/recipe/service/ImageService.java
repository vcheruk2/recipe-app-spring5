package com.ravi.recipe.service;

import org.springframework.web.multipart.MultipartFile;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 4/18/2020 */
public interface ImageService {
    void saveImageFile(Long recipeId, MultipartFile file);
}
