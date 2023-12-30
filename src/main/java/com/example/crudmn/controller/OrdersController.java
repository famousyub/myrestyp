package com.example.crudmn.controller;


import com.example.crudmn.models.Ordersp;
import com.example.crudmn.repository.OrderpRepository;
import com.example.crudmn.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin("*")
public class OrdersController {


    private final OrderpRepository orderRepo;
    private final UserRepository userRepo;

    public OrdersController(OrderpRepository orderRepo, UserRepository userRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    @PostMapping
    public ResponseEntity<?> addOrderItems(Authentication auth,
                                           @RequestBody Ordersp orderReq){
        String id = userRepo.findByEmail(auth.getName()).get().getId();
        orderReq.setUser(id);
        Ordersp savedOrder =  orderRepo.save(orderReq);
        return ResponseEntity.status(201).body(savedOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> addOrderItems(@PathVariable String id){
        Ordersp order = orderRepo.findById(id).get();
        return ResponseEntity.status(201).body(order);
    }

    @GetMapping("/myorders")
    public ResponseEntity<?> getOrders(Authentication auth){
        String user_id = userRepo.findByEmail(auth.getName()).get().getId();
        List<Ordersp> myorders = orderRepo.findAllByUser(user_id);
        return ResponseEntity.ok(myorders);
    }
}
