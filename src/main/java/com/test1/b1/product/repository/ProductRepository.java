package com.test1.b1.product.repository;

import com.test1.b1.product.domain.Product;
import com.test1.b1.product.repository.search.ProductSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductSearch {
}
