package com.ravi.recipe.controller;

import com.ravi.recipe.commands.RecipeCommand;
import com.ravi.recipe.service.ImageService;
import com.ravi.recipe.service.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 4/18/2020 */
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;


    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/imageUploadForm";
    }

    @PostMapping("recipe/{id}/image")
    public String postUploadForm(@PathVariable String id, @RequestParam("imagefile")MultipartFile file){
        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/recipe/"+id+"/show";
    }

    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws Exception {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));

        byte[] byteArray = new byte[recipeCommand.getImage().length];

        int idx = 0;
        for(Byte wrappedByte : recipeCommand.getImage())
            byteArray[idx++] = wrappedByte;

        response.setContentType("image/jpeg");
        InputStream inputStream = new ByteArrayInputStream(byteArray);
        IOUtils.copy(inputStream, response.getOutputStream());
    }
}
