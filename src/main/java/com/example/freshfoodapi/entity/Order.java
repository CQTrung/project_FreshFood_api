package com.example.freshfoodapi.entity;

import com.example.freshfoodapi.constant.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

import java.util.Date;

import java.util.List;
import java.util.Set;

@Entity()
@Table(name  = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Order  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "note")
    private String note;
    @Column(name = "quantity")
    private int quantity;

    private BigDecimal unitPrice;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference(value = "products")
    private List<Product> products;


    @ManyToOne
    @JoinColumn(name = "orderDetail_id")
    @JsonBackReference(value = "order")
    private OrderDetail orderDetail;

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
