package com.example.crudmn.resto;

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
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.example.crudmn.entity.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="tablerestos")
@Data

@NoArgsConstructor
public class TableResto extends UserDateAudit {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(max = 140)
    @Column(unique = true)
    private String tablename;


    private Integer tablenumber;


    @Enumerated(EnumType.STRING)
    //@NaturalId
    @Column(length = 60)
    private TableClass tableclass;


    @Enumerated(EnumType.STRING)
    //@NaturalId
    @Column(length = 60)
    private TableStatus tableavaible;





    public TableResto(long id, @Size(max = 140) String tablename, Integer tablenumber, TableClass tableclass,
                      TableStatus tableavaible, Restaurant restaurant) {
        super();
        this.id = id;
        this.tablename = tablename;
        this.tablenumber = tablenumber;
        this.tableclass = tableclass;
        this.tableavaible = tableavaible;
        this.restaurant = restaurant;
    }





    public TableStatus getTableavaible() {
        return tableavaible;
    }





    public void setTableavaible(TableStatus tableavaible) {
        this.tableavaible = tableavaible;
    }





    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;
}
