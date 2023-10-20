package com.example.freshfoodapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "image")
@NoArgsConstructor
@Getter
@Setter
//@SuperBuilder

public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String name;

    private String type;

    private long size;

    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "product")
    private Product product;

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
