package com.example.crudmn.restorepository;

import com.example.crudmn.resto.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}