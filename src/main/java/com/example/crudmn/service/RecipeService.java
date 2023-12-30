package com.example.crudmn.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.example.crudmn.exception.ResourceNotFoundException;

import com.example.crudmn.resto.Category1;
import com.example.crudmn.resto.Ingredient;
import com.example.crudmn.resto.Recipe;
import com.example.crudmn.restorepository.Category1Repository;
import com.example.crudmn.restorepository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private Category1Repository categoryRepository;

    public Recipe createRecipe(Recipe recipeRequest, long categoryId) {

        logger.info("SV_RecipeService_FN_createRecipe");

        Category1 category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        recipeRequest.setCategory(category);
        recipeRequest.setQunatitymax(150);


        List<Recipe> recipes = recipeRepository.findAllByCategoryId(categoryId);
        recipeRequest.setLevel(recipes.size() + 1);

        for (Ingredient ingredient : recipeRequest.getIngredients()) {
            ingredient.setRecipe(recipeRequest);
        }

        return recipeRepository.save(recipeRequest);

    }

    public Recipe updateRecipe(Long recipeId, Recipe recipeRequest) {

        logger.info("SV_RecipeService_FN_createRecipe");

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

        recipe.setName(recipeRequest.getName());
        recipe.setDescription(recipeRequest.getDescription());
        recipe.setShown(recipeRequest.getShown());
        recipe.setPrice(recipeRequest.getPrice());
        recipe.setImagePath(recipeRequest.getImagePath());
        recipeRequest.setQunatitymax(150);
        recipe.getIngredients().clear();

        int index = 0;
        for (Ingredient ingredient : recipeRequest.getIngredients()) {
            ingredient.setRecipe(recipe);
            ingredient.setLevel(index);
            index++;
            recipe.getIngredients().add(ingredient);
        }

        return recipeRepository.save(recipe);
    }

    public List<Recipe> switchLevelRecipe(Recipe recipeRequest, long recipeId, long level) {

        logger.info("SV_RecipeService_FN_switchLevelRecipe");

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

        long recipeLevel = recipe.getLevel();
        long recipeRepLevel = recipe.getLevel() + level;

        List<Recipe> recipes = recipeRepository.findAllByCategoryId(recipe.getCategory().getId());

        Recipe recipeReplace = new Recipe();
        for (Recipe r : recipes) {
            if (r.getLevel() == recipeRepLevel) {
                recipeReplace = r;
                break;
            }
        }

        recipeReplace.setLevel(recipeLevel);
        recipe.setLevel(recipeRepLevel);
        System.out.println(recipe.getCategory());

        List<Recipe> recipesOfCategory = new ArrayList<Recipe>();
        recipesOfCategory.add(recipe);
        recipesOfCategory.add(recipeReplace);

        return recipeRepository.saveAll(recipesOfCategory);

    }

    public void deleteRecipe(Long recipeId) {

        logger.info("SV_RecipeService_FN_deleteRecipe");

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

        recipeRepository.deleteById(recipeId);

        List<Recipe> recipes = recipeRepository.findAllByCategoryId(recipe.getCategory().getId());

        Comparator<Recipe> c = (s1, s2) -> (int) s1.getLevel() - (int) s2.getLevel();
        recipes.sort(c);

        int index = 1;
        for (Recipe rec : recipes) {
            rec.setLevel(index);
            index++;
        }
        recipeRepository.saveAll(recipes);

    }

    public List<Recipe> getRecipesByCategory(long categoryId) {
        logger.info("SV_RecipeService_FN_getRecipesByCategory");

        Category1 category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        return recipeRepository.findAllByCategoryId(categoryId);
    }

}
