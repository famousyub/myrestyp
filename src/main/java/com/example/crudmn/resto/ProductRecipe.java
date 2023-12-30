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
import javax.persistence.Table;
@Entity
@Table(name="productrecipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRecipe extends UserDateAudit {
    @Id

    @Column(name = "id")

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "name")

    private String name;

    @Column(name = "type")

    private String type;

    @Column(name = "picByte", length = 1000)

    private byte[] picByte;

    private Long reciepeId;
}
