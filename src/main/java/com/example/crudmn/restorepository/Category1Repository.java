package com.example.crudmn.restorepository;

import java.util.List;
import java.util.Optional;


import com.example.crudmn.resto.Category1;
import com.example.crudmn.resto.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Category1Repository extends JpaRepository<Category1, Long> {

    Optional<Category1> findByLevel(long categoryRepLevel);

    List<Category1> findByCompany(Company company);

    List<Category1> findByCompanyId(long companyId);

}