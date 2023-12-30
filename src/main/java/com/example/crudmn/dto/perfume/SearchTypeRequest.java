package com.example.crudmn.dto.perfume;


import com.example.crudmn.enums.SearchPerfume;
import lombok.Data;

@Data
public class SearchTypeRequest {
    private SearchPerfume searchType;
    private String text;
}