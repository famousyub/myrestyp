package com.example.crudmn.service;


import com.example.crudmn.domain.Perfume;
import com.example.crudmn.domain.Review;
import com.example.crudmn.entity.Userpoll;
import graphql.schema.DataFetcher;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Userpoll getUserById(Long userId);

    Userpoll getUserInfo(String email);

    Page<Userpoll> getAllUsers(Pageable pageable);

    List<Perfume> getCart(List<Long> perfumeIds);

    Userpoll updateUserInfo(String email, Userpoll user);

    Review addReviewToPerfume(Review review, Long perfumeId);

    DataFetcher<List<Userpoll>> getAllUsersByQuery();

    DataFetcher<Userpoll> getUserByQuery();
}