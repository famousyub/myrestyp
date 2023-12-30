package com.example.crudmn.restorepository;

import java.util.Optional;

import com.example.crudmn.resto.Commande;
import com.example.crudmn.resto.Equipement;
import com.example.crudmn.resto.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EquipementRepository extends JpaRepository<Equipement, Long> {

    @Override
    Optional<Equipement> findById(Long baseStationId);

    Page<Equipement> findByCreatedBy(Long userId, Pageable pageable);

    long countByCreatedBy(Long userId);

    Optional<Equipement> findTopByStateAndCommandeAndConnected(State state, Commande commande, boolean connected);

    Optional<Equipement> findByIpAddress(String equipementIpAddress);

}