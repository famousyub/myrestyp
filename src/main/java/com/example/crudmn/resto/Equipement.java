package com.example.crudmn.resto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.crudmn.entity.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "equipements", uniqueConstraints = { @UniqueConstraint(columnNames = { "commande_id" }) })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipement  extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(max = 140)
    @Column(unique = true)
    private String name;

    @Size(max = 140)
    private String model;

    @Size(max = 140)
    @Column(unique = true)
    private String serialNumber;

    @Size(max = 140)
    private String firmwareVersion;

    @Column(nullable = true)
    private LocalDateTime lastSeen;

    @Size(max = 140)
    private String login;

    @Size(max = 140)
    private String password;

    @Size(max = 140)
    @Column(unique = true)
    private String macAddress;

    @Size(max = 140)
    @Column(unique = true)
    private String ipAddress;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @Enumerated(EnumType.STRING)
    private State state;

    private float batteryLevel;

    private boolean connected;

    private boolean alarm;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    private Commande commande;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "network_id", nullable = false)
    private Network network;

    @OneToMany(mappedBy = "equipement", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Size(min = 0, max = 10)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 30)
    private List<Notification> notifications = new ArrayList<>();
}
