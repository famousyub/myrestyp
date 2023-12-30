package com.example.crudmn.repository;

import com.example.crudmn.entity.Userpoll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPageRepository extends JpaRepository<Userpoll, Long> {

    List<Userpoll> findAllByOrderByIdAsc();

    Page<Userpoll> findAllByOrderByIdAsc(Pageable pageable);

    Optional<Userpoll> findByActivationCode(String code);

    Optional<Userpoll> findByEmail(String email);

    @Query("SELECT user.email FROM Userpoll user WHERE user.passwordResetCode = :code")
    Optional<String> getEmailByPasswordResetCode(String code);
}
