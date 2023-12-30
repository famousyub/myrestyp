package com.example.crudmn.controller;


import com.example.crudmn.entity.UserDetail;
import com.example.crudmn.models.Products;
import com.example.crudmn.models.Review;
import com.example.crudmn.models.User;
import com.example.crudmn.repository.*;
import com.example.crudmn.security.services.UserDetailsImpl;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin("*")
public class ProductsController {


    private final ProductsRepository productRepo;
    private final UserRepository userRepo;

    public ProductsController(ProductsRepository productRepo, UserRepository userRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }



    @PostMapping("/add")
    public  ResponseEntity<?> addproduct(@RequestBody Products prod, Authentication auth)
    {
        UserDetailsImpl i = (UserDetailsImpl) auth.getPrincipal();
        User me = this.userRepo.findByEmail(i.getEmail()).get();

        prod.setUser(me.getUsername());
        prod.setCreatedAt(Instant.now());
        prod.setUpdatedAt(Instant.now());
        productRepo.save(prod);

        return  ResponseEntity.ok().body(prod);

    }

    @GetMapping("/top")
    public ResponseEntity<?> getTopProducts(){

        List<TopProductRes> products;
        try(Stream<TopProductRes> stream = productRepo.getTopProducts()) {
            products = stream.limit(3).collect(Collectors.toList());
        }
        System.out.println(products);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/all")
    public  ResponseEntity<?> allproduct(){
        List<Products> all_prod = productRepo.findAll();
        return  ResponseEntity.ok().body(all_prod);
    }
    @GetMapping
    public ResponseEntity<Map<String, Object>> getProducts(@RequestParam(value = "keyword") String keyword,
                                                           @RequestParam(value = "pageNumber") int pageNumber){
        String key = keyword.strip();
        Pageable pageable = PageRequest.of(pageNumber - 1, 8);
        if (key.length() == 0){
            Page<Products> products = productRepo.findAll(pageable);
            List<Products> p = products.getContent();
            int page = products.getNumber() + 1;
            int pages = products.getTotalPages();
            Map<String, Object> result = Map.of("page", page, "pages", pages, "products", p);
            return ResponseEntity.ok(result);
        } else {
            Page<Products> products = productRepo.findAllByQ(key, pageable);
            List<Products> p = products.getContent();
            int page = products.getNumber() + 1;
            int pages = products.getTotalPages();
            Map<String, Object> result = Map.of("page", page, "pages", pages, "products", p);
            return ResponseEntity.ok(result);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        Products p = productRepo.findById(id).get();
        List<Review> r =  new ArrayList<>();
        p.setReviews(r);
        return ResponseEntity.ok(p);
    }


    //@PostMapping("/{id}/reviews")
    @PostMapping("/r/{id}")
    public ResponseEntity<?> addComment(Authentication auth, @PathVariable String id, @RequestBody CommentReq req){
        User user = userRepo.findByEmail(auth.getName()).get();
        Optional<Products> p = productRepo.findById(id);
        if (p.isPresent()){
            Products product = p.get();
            var alreadyReviewed = product.getReviews().stream().filter(x -> x.getUser().equals(user.getId())).count();
            if (alreadyReviewed != 0){
                return ResponseEntity.status(400).body(new MessageRes("Product already reviewed"));
            }
            Review r = new Review();
            r.setRating(req.getRating());
            r.setComment(req.getComment());
            r.setName(user.getUsername());
            r.setUser(user.getId());
            product.getReviews().add(r);
            int numReviews = product.getNumReviews() + 1;
            Double rating = (product.getRating() + req.getRating())/numReviews;
            product.setNumReviews(numReviews);
            DecimalFormat df = new DecimalFormat("#.0");
            product.setRating(Double.valueOf(df.format(rating)));
            productRepo.save(product);
            return ResponseEntity.status(201).body(new MessageRes("Review added"));

        }
        return ResponseEntity.ok(p);
    }
}
