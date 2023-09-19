package com.example.freshfoodapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "address")
    private String address;
    @Column(name = "note")
    private BigDecimal totalPrice;

    @Column(name = "status")
    private  int status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "order")
    private Set<Payment> payments;

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
