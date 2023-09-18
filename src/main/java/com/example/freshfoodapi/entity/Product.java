package com.example.freshfoodapi.entity;

import io.jsonwebtoken.lang.Classes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
@SuperBuilder
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "productName", nullable = false)
    private String productName;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "weight")
    private float weight;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "madein")
    private String madeIn;

    @ManyToOne
    @JoinColumn(name = "warehouse_id",nullable = false)
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @ManyToMany
    @JoinTable(name = "category_product",
               joinColumns = @JoinColumn(name = "product_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categoryList;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetailList;





}
