package com.example.crudmn.repository;

import com.example.crudmn.models.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.stream.Stream;


public interface ProductsRepository extends MongoRepository<Products,String> {

    @Query(value="{}",
            sort="{rating: -1}",
            fields = "{ _id: 1 , name: 1, image: 1, price:1 }")
    Stream<TopProductRes> getTopProducts();

    @Query(value = "{name: {$regex: ?0, $options: 'i'}}")
    Page<Products> findAllByQ(String query, Pageable pageable);
}