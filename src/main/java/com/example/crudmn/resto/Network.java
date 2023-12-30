package com.example.crudmn.resto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.crudmn.entity.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "networks", uniqueConstraints = { @UniqueConstraint(columnNames = { "reference" }) })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Network  extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 140)
    private String reference;

    @NotBlank
    @Size(max = 140)
    private String country;

    @DecimalMin(value = "-90")
    @DecimalMax(value = "90")
    private double gpsLat;

    @DecimalMin(value = "-180")
    @DecimalMax(value = "180")
    private double gpsLong;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "network", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Size(min = 0, max = 10)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 30)
    private List<Equipement> equipements = new ArrayList<>();

}
