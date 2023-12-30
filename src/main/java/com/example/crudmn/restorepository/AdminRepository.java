package com.example.crudmn.restorepository;

import com.example.crudmn.resto.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface AdminRepository extends JpaRepository<Company, Long> {

    //@Query("SELECT c FROM  Company  c  WHERE   c. id  = (select e.company.id from Employee e  where e.id = ?1)")

    //Company getCompanyAdmin(long adminid);

}