package com.example.freshfoodapi.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity()
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "weight")
    private float weight;
    @Column(name = "description")
    private String description;

    @Column(name = "madeIn")
    private String madeIn;

    @Column(name = "quantity")
    private  int quantity;


    @OneToMany(mappedBy = "product")
    @JsonManagedReference(value = "images")
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    @JsonBackReference(value = "sale")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference(value = "category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference(value = "order")
    private Order order;


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
