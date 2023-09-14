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
    @JoinColumn(name = "warehouse_id",referencedColumnName = "id",nullable = false)
    //warehouse_id chính là trường khoá phụ trong table Product liên kết với khóa chính trong table Warehouse
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "sale_id",referencedColumnName = "id", nullable = false)
    //warehouse_id chính là trường khoá phụ trong table Product liên kết với khóa chính trong table Warehouse
    private Sale sale;

    @ManyToMany
    @JoinTable(name = "category_product",
               joinColumns = @JoinColumn(name = "product_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categoryList;





}
