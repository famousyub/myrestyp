package com.example.crudmn.controller;

import com.example.crudmn.dto.GraphQLRequest;
import com.example.crudmn.dto.HeaderResponse;
import com.example.crudmn.dto.order.OrderResponse;
import com.example.crudmn.dto.perfume.FullPerfumeResponse;
import com.example.crudmn.dto.perfume.PerfumeRequest;
import com.example.crudmn.mapper.OrderMapper;
import com.example.crudmn.mapper.PerfumeMapper;
import com.example.crudmn.mapper.UserMapper;
import com.example.crudmn.payload.response.UserInfoResponse;
import com.example.crudmn.service.GraphQLProvider;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/api/v1/admin")
@CrossOrigin("*")
public class AdminController {

    private final UserMapper userMapper;
    private final PerfumeMapper perfumeMapper;
    private final OrderMapper orderMapper;
    private final GraphQLProvider graphQLProvider;

    @PostMapping("/add")
    public ResponseEntity<FullPerfumeResponse> addPerfume(@RequestPart(name = "file", required = false) MultipartFile file,
                                                          @RequestPart("perfume") @Valid PerfumeRequest perfume,
                                                          BindingResult bindingResult) {
        return ResponseEntity.ok(perfumeMapper.savePerfume(perfume, file, bindingResult));
    }

    @PostMapping("/edit")
    public ResponseEntity<FullPerfumeResponse> updatePerfume(@RequestPart(name = "file", required = false) MultipartFile file,
                                                             @RequestPart("perfume") @Valid PerfumeRequest perfume,
                                                             BindingResult bindingResult) {
        return ResponseEntity.ok(perfumeMapper.savePerfume(perfume, file, bindingResult));
    }

    @DeleteMapping("/delete/{perfumeId}")
    public ResponseEntity<String> deletePerfume(@PathVariable Long perfumeId) {
        return ResponseEntity.ok(perfumeMapper.deletePerfume(perfumeId));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders(@PageableDefault(size = 10) Pageable pageable) {
        HeaderResponse<OrderResponse> response = orderMapper.getAllOrders(pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @GetMapping("/order/{userEmail}")
    public ResponseEntity<List<OrderResponse>> getUserOrdersByEmail(@PathVariable String userEmail,
                                                                    @PageableDefault(size = 10) Pageable pageable) {
        HeaderResponse<OrderResponse> response = orderMapper.getUserOrders(userEmail, pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @DeleteMapping("/order/delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderMapper.deleteOrder(orderId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserInfoResponse> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userMapper.getUserById(userId));
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<UserInfoResponse>> getAllUsers(@PageableDefault(size = 10) Pageable pageable) {
        HeaderResponse<UserInfoResponse> response = (HeaderResponse<UserInfoResponse>) userMapper.getAllUsers(pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @PostMapping("/graphql/user")
    public ResponseEntity<ExecutionResult> getUserByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @PostMapping("/graphql/user/all")
    public ResponseEntity<ExecutionResult> getAllUsersByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @PostMapping("/graphql/orders")
    public ResponseEntity<ExecutionResult> getAllOrdersQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @PostMapping("/graphql/order")
    public ResponseEntity<ExecutionResult> getUserOrdersByEmailQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }
}