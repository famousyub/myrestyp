package com.example.crudmn.dto.order;


import com.example.crudmn.dto.perfume.PerfumeResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {
    private Long id;
    private Long amount;
    private Long quantity;
    private PerfumeResponse perfume;
}