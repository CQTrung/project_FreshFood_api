package com.example.freshfoodapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;


@Entity()
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Payment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "inserted_time", nullable = true)
    private Date insertedTime;
    @Column(name = "updated_time", nullable = true)
    private Date updatedTime;

    @Column(name = "isDeleted", nullable = true)
    private Boolean isDeleted;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private OrderDetail  orderDetail;

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
