package com.example.freshfoodapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

<<<<<<< HEAD
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "payments")
@SuperBuilder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;
    @Column(name = "created_time", nullable = true)
    private Date createdTime;
    @Column(name = "updated_time", nullable = true)
    private Date updatedTime;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id", nullable = false)
    //warehouse_id chính là trường khoá phụ trong table Product liên kết với khóa chính trong table Warehouse
    private Order order;
=======
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
    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;
    @Column(name = "status")
    private  int status;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
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
>>>>>>> b54d56492e30cd1470fd67bacdcaa7da428c7876


}
