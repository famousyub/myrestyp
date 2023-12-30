package com.example.crudmn.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.crudmn.models.Tutrial;

@Repository
public interface TutrialRepository extends MongoRepository<Tutrial, String> {

	
	 List<Tutrial>  findByTitleContaining(String title);
	 List<Tutrial>  findByPublished(boolean published);
	 
}
