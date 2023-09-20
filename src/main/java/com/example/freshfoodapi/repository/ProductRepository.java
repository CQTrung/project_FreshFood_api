package com.example.freshfoodapi.repository;


import com.example.freshfoodapi.dto.ProductDto;
import com.example.freshfoodapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor {

    /*@Query("select new com.example.freshfoodapi.dto.ProductDto (p.name, p.price, c.name)" +
            "from Product p inner join Category c on p.categoryId = c.id where c.name like ('Sale 50% product')")
    List<ProductDto> Product();
*/
    Optional<ProductDto> findByName(String name);

    List<ProductDto> findAllProduct();
}
