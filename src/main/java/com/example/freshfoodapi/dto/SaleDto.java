package com.example.freshfoodapi.dto;

import com.example.freshfoodapi.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDto extends PageDto{
    private long id;
    private int discount;
    private String codeSale;
    private Date startDay;
    private Date endDay;
    private List<Product> productList;
}
