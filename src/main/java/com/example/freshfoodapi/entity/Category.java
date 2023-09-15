package com.example.freshfoodapi.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "category")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Category extends BaseEntity{

    @Column(name = "name")
    private String name;
    @Column(name = "inserted_time", nullable = true)
    private Date insertedTime;
    @Column(name = "updated_time", nullable = true)
    private Date updatedTime;

    @ManyToMany(mappedBy = "categoryList",cascade = CascadeType.ALL)
    private List<Product> productList;

}
