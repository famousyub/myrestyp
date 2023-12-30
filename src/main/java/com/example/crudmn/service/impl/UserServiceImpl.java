package com.example.crudmn.service.impl;

import com.example.crudmn.domain.Perfume;
import com.example.crudmn.domain.Review;
import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.exception.ApiRequestException;
import com.example.crudmn.repository.*;
import com.example.crudmn.service.UserService;

import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserPageRepository userRepository;
    private final PerfumeRepository perfumeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Userpoll getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiRequestException("User not found.", HttpStatus.NOT_FOUND));
    }

    @Override
    public Userpoll getUserInfo(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("Email not found.", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<Userpoll> getAllUsers(Pageable pageable) {
        return userRepository.findAllByOrderByIdAsc(pageable);
    }

    @Override
    public List<Perfume> getCart(List<Long> perfumeIds) {
        return perfumeRepository.findByIdIn(perfumeIds);
    }

    @Override
    @Transactional
    public Userpoll updateUserInfo(String email, Userpoll user) {
        Userpoll userFromDb = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("Email not found.", HttpStatus.NOT_FOUND));
        userFromDb.setFirstname(user.getFirstname());
        userFromDb.setLastname(user.getLastname());
        //userFromDb.set(user.getCity());
        //userFromDb.setAddress(user.getAddress());
        userFromDb.setPhonenumber(user.getPhonenumber());
        userFromDb.setName(user.getUsername());
        return userFromDb;
    }

    @Override
    @Transactional
    public Review addReviewToPerfume(Review review, Long perfumeId) {
        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new ApiRequestException("Perfume not found.", HttpStatus.NOT_FOUND));
        List<Review> reviews = perfume.getReviews();
        reviews.add(review);
        double totalReviews = reviews.size();
        double sumRating = reviews.stream().mapToInt(Review::getRating).sum();
        perfume.setPerfumeRating(sumRating / totalReviews);
        return reviewRepository.save(review);
    }

    @Override
    public DataFetcher<Userpoll> getUserByQuery() {
        return dataFetchingEnvironment -> {
            Long userId = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
            return userRepository.findById(userId).get();
        };
    }

    @Override
    public DataFetcher<List<Userpoll>> getAllUsersByQuery() {
        return dataFetchingEnvironment -> userRepository.findAllByOrderByIdAsc();
    }
}