package com.example.crudmn.resto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RestoResponse {

    private Restaurant  resto ;

    private List<Recipe> recipes ;
}
