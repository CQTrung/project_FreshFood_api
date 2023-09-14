package com.example.freshfoodapi.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "sales")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Sale extends BaseEntity{

    @Column(name = "persent")
    private String persent;
    @Column(name = "codeSale")
    private String codeSale;
    @Column(name = "startDay")
    private Date startDay;
    @Column(name = "endDay")
    private Date endDay;

    @OneToMany(mappedBy = "sale")
    private List<Product> productList;

}
