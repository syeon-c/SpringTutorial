package com.test1.b1.product.repository.search;

import com.test1.b1.product.dto.ProductListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductSearch {

    Page<ProductListDTO> getList(Pageable pageable);

}
