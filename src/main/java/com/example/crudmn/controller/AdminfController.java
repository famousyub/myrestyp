package com.example.crudmn.controller;


import com.example.crudmn.domain.Order;
import com.example.crudmn.domain.Perfume;
import com.example.crudmn.dto.GraphQLRequest;
import com.example.crudmn.dto.HeaderResponse;
import com.example.crudmn.dto.order.OrderResponse;
import com.example.crudmn.dto.perfume.PerfumeRequest;
import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.service.GraphQLProvider;
import com.example.crudmn.service.OrderService;
import com.example.crudmn.service.PerfumeService;
import com.example.crudmn.service.UserService;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
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
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/v1/admin1")
@CrossOrigin("*")
public class AdminfController {


    private final GraphQLProvider graphQLProvider;

    @Autowired
    private PerfumeService perfumeService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;
    @PostMapping("/add")
    public ResponseEntity<Perfume> addPerfume(@RequestPart(name = "file", required = false) MultipartFile file,
                                              @RequestPart("perfume") @Valid PerfumeRequest perfume,
                                              BindingResult bindingResult) {
        Perfume perfume1 = new Perfume();
        perfume1.setPerfumer(perfume.getPerfumer());
        perfume1.setCountry(perfume.getCountry());
        perfume1.setPerfumeRating(Double.parseDouble("0"));
        perfume1.setPerfumeGender(perfume.getPerfumeGender());
        perfume1.setType(perfume.getType());
        perfume1.setPerfumeTitle(perfume.getPerfumeTitle());
        perfume1.setDescription("good");
        perfume1.setPrice(perfume.getPrice());

        return ResponseEntity.ok(perfumeService.savePerfume(perfume1, file));
    }

    @PostMapping("/edit")
    public ResponseEntity<Perfume> updatePerfume(@RequestPart(name = "file", required = false) MultipartFile file,
                                                             @RequestPart("perfume") @Valid PerfumeRequest perfume,
                                                             BindingResult bindingResult) {

        Perfume perfume1 = new Perfume();
        perfume1.setId(perfume.getId());
        perfume1.setPerfumer(perfume.getPerfumer());
        perfume1.setCountry(perfume.getCountry());
        perfume1.setPerfumeRating(Double.parseDouble("0"));
        perfume1.setPerfumeGender(perfume.getPerfumeGender());
        perfume1.setType(perfume.getType());
        perfume1.setPerfumeTitle(perfume.getPerfumeTitle());
        perfume1.setDescription("good");
        perfume1.setPrice(perfume.getPrice());
        return ResponseEntity.ok(perfumeService.savePerfume(perfume1, file));
    }

    @DeleteMapping("/delete/{perfumeId}")
    public ResponseEntity<String> deletePerfume(@PathVariable Long perfumeId) {
        return ResponseEntity.ok(perfumeService.deletePerfume(perfumeId));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders(@PageableDefault(size = 10) Pageable pageable) {
        HeaderResponse<OrderResponse> response = (HeaderResponse<OrderResponse>) orderService.getAllOrders(pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @GetMapping("/order/{userEmail}")
    public ResponseEntity<List<Order>> getUserOrdersByEmail(@PathVariable String userEmail,
                                                                    @PageableDefault(size = 10) Pageable pageable) {
        HeaderResponse<Order> response = (HeaderResponse<Order>) orderService.getUserOrders(userEmail, pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @DeleteMapping("/order/delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.deleteOrder(orderId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Userpoll> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<Userpoll>> getAllUsers(@PageableDefault(size = 10) Pageable pageable) {
        HeaderResponse<Userpoll> response = (HeaderResponse<Userpoll>) userService.getAllUsers(pageable);
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
