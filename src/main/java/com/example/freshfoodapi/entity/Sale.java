package com.example.freshfoodapi.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "sale")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "discount")
    private int discount;
    @Column(name = "codeSale",unique = true)
    private String codeSale;
    @Column(name = "startDay")
    private Date startDay;
    @Column(name = "endDay")
    private Date endDay;

    @OneToMany(mappedBy = "sale")
    private List<Product> productList;
    @Column(name = "inserted_time", nullable = true)
    private Date insertedTime;
    @Column(name = "updated_time", nullable = true)
    private Date updatedTime;

    @Column(name = "isDeleted", nullable = true)
    private Boolean isDeleted;

    @PrePersist
    private void beforeInsert() {
        this.insertedTime = new Date();
        this.isDeleted = false;
    }

    @PreUpdate
    private void beforeUpdate() {
        this.updatedTime = new Date();
    }

}
