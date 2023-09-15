package com.example.freshfoodapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order_details")
@SuperBuilder
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id", nullable = false)
    //warehouse_id chính là trường khoá phụ trong table Product liên kết với khóa chính trong table Warehouse
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id", nullable = false)
    private Product product;

    private int quantity;
    private BigDecimal unitPrice;
    @Column(name = "inserted_time", nullable = true)
    private Date insertedTime;
    @Column(name = "updated_time", nullable = true)
    private Date updatedTime;
}
