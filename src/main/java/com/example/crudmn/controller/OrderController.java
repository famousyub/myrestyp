package com.example.crudmn.controller;

import com.example.crudmn.dto.GraphQLRequest;
import com.example.crudmn.dto.HeaderResponse;
import com.example.crudmn.dto.order.OrderItemResponse;
import com.example.crudmn.dto.order.OrderRequest;
import com.example.crudmn.dto.order.OrderResponse;
import com.example.crudmn.dto.perfume.PerfumeResponse;
import com.example.crudmn.dto.review.ReviewRequest;
import com.example.crudmn.dto.review.ReviewResponse;
import com.example.crudmn.dto.user.UserResponse;
import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.mapper.OrderMapper;
import com.example.crudmn.mapper.UserMapper;
import com.example.crudmn.payload.request.UserInfoRequest;
import com.example.crudmn.payload.response.UserInfoResponse;
import com.example.crudmn.repository.UserPollRepository;
import com.example.crudmn.security.services.UserDetailsImpl;
import com.example.crudmn.service.GraphQLProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@CrossOrigin("*")
public class OrderController {

    private final UserMapper userMapper;
    private final OrderMapper orderMapper;
    private final GraphQLProvider graphQLProvider;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserPollRepository userPollRepository;

    @GetMapping("/info")
    //@AuthenticationPrincipal UserPrincipal user
    public ResponseEntity<UserResponse> getUserInfo(Authentication currentUser) {

        UserDetails u = (UserDetails) currentUser.getPrincipal();

        Userpoll userpoll = userPollRepository.findByUsername(u.getUsername()).get();

        UserDetailsImpl user_ = new UserDetailsImpl(userpoll.getId().toString(),userpoll.getUsername(),userpoll.getEmail());
        return ResponseEntity.ok(userMapper.getUserInfo(user_.getEmail()));
    }

    @PutMapping("/edit")
    public ResponseEntity<UserInfoResponse> updateUserInfo(Authentication currentUser,
                                                       @Valid @RequestBody UserInfoRequest request,
                                                       BindingResult bindingResult) {


        UserDetails u = (UserDetails) currentUser.getPrincipal();

        Userpoll userpoll = userPollRepository.findByUsername(u.getUsername()).get();

        UserDetailsImpl user_ = new UserDetailsImpl(userpoll.getId().toString(),userpoll.getUsername(),userpoll.getEmail());
        return ResponseEntity.ok(userMapper.updateUserInfo(user_.getEmail(), request, bindingResult));
    }

    @PostMapping("/cart")
    public ResponseEntity<List<PerfumeResponse>> getCart(@RequestBody List<Long> perfumesIds) {
        return ResponseEntity.ok(userMapper.getCart(perfumesIds));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderMapper.getOrderById(orderId));
    }

    @GetMapping("/order/{orderId}/items")
    public ResponseEntity<List<OrderItemResponse>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderMapper.getOrderItemsByOrderId(orderId));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getUserOrders(Authentication currentUser,
                                                             @PageableDefault(size = 10) Pageable pageable) {


        UserDetails u = (UserDetails) currentUser.getPrincipal();

        Userpoll userpoll = userPollRepository.findByUsername(u.getUsername()).get();

        UserDetailsImpl user_ = new UserDetailsImpl(userpoll.getId().toString(),userpoll.getUsername(),userpoll.getEmail());
        HeaderResponse<OrderResponse> response = orderMapper.getUserOrders(user_.getEmail(), pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> postOrder(@Valid @RequestBody OrderRequest order, BindingResult bindingResult) {
        return ResponseEntity.ok(orderMapper.postOrder(order, bindingResult));
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewResponse> addReviewToPerfume(@Valid @RequestBody ReviewRequest reviewRequest,
                                                             BindingResult bindingResult) {
        ReviewResponse review = userMapper.addReviewToPerfume(reviewRequest, reviewRequest.getPerfumeId(), bindingResult);
        messagingTemplate.convertAndSend("/topic/reviews/" + reviewRequest.getPerfumeId(), review);
        return ResponseEntity.ok(review);
    }

    @PostMapping("/graphql/info")
    public ResponseEntity<ExecutionResult> getUserInfoByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @PostMapping("/graphql/orders")
    public ResponseEntity<ExecutionResult> getUserOrdersByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }
}
