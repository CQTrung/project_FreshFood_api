package com.example.freshfoodapi.entity;

import com.example.freshfoodapi.constant.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity()
@Table(name = "order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@SuperBuilder
public class OrderDetail implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference(value = "order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference(value = "product")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    private BigDecimal totalPrice;

    @Column(name = "status")
    private Status status;

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
        this.status=Status.WAITING;
    }
    @PreUpdate
    private void beforeUpdate() {
        this.updatedTime = new Date();
    }

}
