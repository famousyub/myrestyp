package com.example.crudmn.resto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;


@Data
@NoArgsConstructor
public class RestoTableResponse {


    private Restaurant  resto ;

    private List<Recipe> recipes ;


    private Collection<TableResto> tables;
}
