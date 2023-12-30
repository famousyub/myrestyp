package com.example.crudmn.mapper;

import com.example.crudmn.domain.Review;
import com.example.crudmn.dto.HeaderResponse;
import com.example.crudmn.dto.perfume.PerfumeResponse;
import com.example.crudmn.dto.review.ReviewRequest;
import com.example.crudmn.dto.review.ReviewResponse;
import com.example.crudmn.dto.user.UserResponse;
import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.exception.InputFieldException;
import com.example.crudmn.payload.request.UserInfoRequest;
import com.example.crudmn.payload.response.UserInfoResponse;
import com.example.crudmn.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final CommonMapper commonMapper;
    private final UserService userService;

    public UserInfoResponse getUserById(Long userId) {
        return commonMapper.convertToResponse(userService.getUserById(userId), UserInfoResponse.class);
    }

    public UserResponse getUserInfo(String email) {
        return commonMapper.convertToResponse(userService.getUserInfo(email), UserResponse.class);
    }

    public List<PerfumeResponse> getCart(List<Long> perfumesIds) {
        return commonMapper.convertToResponseList(userService.getCart(perfumesIds), PerfumeResponse.class);
    }

    public HeaderResponse<?> getAllUsers(Pageable pageable) {
        Page<Userpoll> users = userService.getAllUsers(pageable);
        return commonMapper.getHeaderResponse(users.getContent(), users.getTotalPages(), users.getTotalElements(), UserInfoResponse.class);
    }

    public UserInfoResponse updateUserInfo(String email, UserInfoRequest userRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        Userpoll user = commonMapper.convertToEntity(userRequest, Userpoll.class);
        return commonMapper.convertToResponse(userService.updateUserInfo(email, user), UserInfoResponse.class);
    }

    public ReviewResponse addReviewToPerfume(ReviewRequest reviewRequest, Long perfumeId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        Review review = commonMapper.convertToEntity(reviewRequest, Review.class);
        return commonMapper.convertToResponse(userService.addReviewToPerfume(review, perfumeId), ReviewResponse.class);
    }
}