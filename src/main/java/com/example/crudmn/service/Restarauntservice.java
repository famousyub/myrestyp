package com.example.crudmn.service;

import com.example.crudmn.resto.Restaurant;
import com.example.crudmn.resto.RestoResponse;
import com.example.crudmn.resto.RestoTableResponse;
import com.example.crudmn.resto.TableResto;

import java.util.Collection;
import java.util.List;

public interface Restarauntservice {

    public Restaurant createResto(Restaurant res , Long companyId);

    public List<Restaurant>  getAllResto(Long companyId);



    public List<RestoResponse>  getRestoswitcher(Long companyId);
    public RestoResponse getRestoById(Long compId , Long restoId);

    public RestoTableResponse  getRestoBytableId(Long compid, Long restoId, Long tableId);

    public  List<RestoTableResponse> getRestoTablerecipes(Long compid, Long restoId, Long tableId);

    public  Boolean isTableAvaible(TableResto resto);

    public List<TableResto> getAvaibleTable(Long restoId);
    public Collection<TableResto> getRestoTableAvaible(Long restoId);


    public  List<RestoTableResponse> getalltableinresto(Long comId);




}