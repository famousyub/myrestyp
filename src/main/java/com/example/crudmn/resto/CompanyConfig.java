package com.example.crudmn.resto;

import com.example.crudmn.entity.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "companyconfig")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyConfig  extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @OneToOne(mappedBy = "conpanyconfig")
    private Company company;

    private String fcbLink;
    private String  instaLink;
    private Long positionLong;

    private Integer serverCallValidity;


    private Integer commandCallValidity;

    @Lob
    @Column(name="logo",nullable = true,updatable=true)
    private byte[] logo;
}
