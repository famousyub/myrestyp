package com.example.crudmn.restorepository;

import java.util.List;

import com.example.crudmn.resto.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findAllByCategoryId(long categoryId);

    //List<Recipe> findAllByRestCategoryId(long categoryId,Long Restid);


    @Query("select re from Recipe re where re.restaurant.id =?1")
    List<Recipe>  findRecipeByrestoId(Long res_id);



}
