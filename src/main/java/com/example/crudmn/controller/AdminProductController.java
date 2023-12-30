package com.example.crudmn.controller;


import com.example.crudmn.entity.Product;
import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.payload.ProductRequest;
import com.example.crudmn.payload.ProductResponse;
import com.example.crudmn.repository.ProductRepository;
import com.example.crudmn.repository.UserPollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin("*")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserPollRepository userPollRepository;


    private byte[] img ;


    @PostMapping("/upload")
    public ResponseEntity<?> uploader(@RequestParam("file") MultipartFile file) throws IOException {

        this.img = file.getBytes() ;

        return  ResponseEntity.ok().body("ok");
    }



    @PostMapping("/add")
    public ResponseEntity<?> addproduct(Authentication me ,@RequestBody ProductRequest prod)
    {
          Product prod_ = new Product();

          prod_.setCode(prod.getCode());
          prod_.setPrice(prod.getPrice());
          prod_.setName(prod.getName());
          prod_.setImage(this.img);
        ProductResponse productResponse =new ProductResponse();
        productResponse.setCode(prod.getCode());
        productResponse.setName(prod.getName());
        productResponse.setPrice(prod.getPrice());
        productResponse.setId(prod_.getId());

        productRepository.save(prod_);

        return  ResponseEntity.ok().body(productResponse);


    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> productDetails(@PathVariable("id") Long id){

        Product pro = productRepository.findById(id).get();
        return  ResponseEntity.ok().body(pro);

    }

    @GetMapping("/all")
    public ResponseEntity<?> allprodicts()
    {
        List<Product> allproduct = productRepository.findAll();
        return  ResponseEntity.ok().body(allproduct);

    }

    @GetMapping("/byname/{name}")
    public  ResponseEntity<?> productDetailsname(@PathVariable("name") String id){

        Product pro = (Product) productRepository.findAll().stream().map(m ->{
            boolean b = m.getName() == id;
            return  m ;
        });
        return  ResponseEntity.ok().body(pro);

    }

}
