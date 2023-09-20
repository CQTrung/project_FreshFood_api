package com.example.freshfoodapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity()
@Table(name = "order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrderDetail {
<<<<<<< HEAD
=======
<<<<<<< HEAD

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id", nullable = false)
    //warehouse_id chính là trường khoá phụ trong table Product liên kết với khóa chính trong table Warehouse
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id", nullable = false)
=======
>>>>>>> duc29
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
<<<<<<< HEAD
=======
>>>>>>> b54d56492e30cd1470fd67bacdcaa7da428c7876
>>>>>>> duc29
    private Product product;
    private int quantity;
    private BigDecimal unitPrice;
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
