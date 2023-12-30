package com.example.crudmn.resto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;


import com.example.crudmn.entity.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "commandes", uniqueConstraints = { @UniqueConstraint(columnNames = { "equipement_id" }) })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande extends UserDateAudit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private boolean paid;

    private int number;

    private float price;

    private int waitingTime;

    private String tableRef;

    private boolean emporter;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CommandState commandState;

    @Lob
    private String recipesJson;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    private Equipement equipement;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private Company company;
}
