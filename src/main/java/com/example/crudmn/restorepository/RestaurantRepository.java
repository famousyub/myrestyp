package com.example.crudmn.restorepository;

import java.util.List;

import com.example.crudmn.resto.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{


    @Query("select r from Restaurant r  where r.company.id =?1")
    List<Restaurant>  getListRestobyCompanyId(Long id);

    @Query("select r.id from Restaurant r ")

    List<Long>  getRestoId();



}