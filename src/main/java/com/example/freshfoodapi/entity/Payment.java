package com.example.freshfoodapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

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


}
