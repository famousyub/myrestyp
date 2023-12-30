package com.example.crudmn.resto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.crudmn.entity.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "companies")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Company extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(unique = true)
    @Size(max = 140)
    private String name;

    // @NotBlank
    @Size(max = 140)
    private String language;

    // @NotBlank
    @Size(max = 140)
    private String logo;

    // @NotBlank
    @Size(max = 140)
    private String timeFormat;

    // @NotBlank
    @Size(max = 140)
    private String dateFormat;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Size(min = 0, max = 10)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 30)
    private List<Licences> licences = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Commande> commands = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyconfig_id", referencedColumnName = "id", nullable=true)
    private CompanyConfig conpanyconfig;

    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Restaurant> restaurants = new ArrayList<>();



    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Size(min = 0, max = 10)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 30)
    private List<Network> networks = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Category1> categories = new ArrayList<>();

}
