package com.test1.b1.product.repository.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.test1.b1.product.domain.Product;
import com.test1.b1.product.domain.QFancy;
import com.test1.b1.product.domain.QProduct;
import com.test1.b1.product.domain.QReview;
import com.test1.b1.product.dto.ProductListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch{

    public ProductSearchImpl() {
        super(Product.class);
    }

    @Override
    public Page<ProductListDTO> getList(Pageable pageable) {

        QProduct product = QProduct.product;
        QReview review = QReview.review;
        QFancy fancy = QFancy.fancy;

        JPQLQuery<Product> query =
                from(product);

        query.leftJoin(review).on(review.product.eq(product));
        query.leftJoin(fancy).on(fancy.product.eq(product));

        query.groupBy(product);

        JPQLQuery<ProductListDTO> dtojpqlQuery =
                query.select(
                        Projections.bean(ProductListDTO.class,
                                product.pno,
                                product.pname,
                                product.price,
                                review.countDistinct().as("reviewCnt"),
                                fancy.countDistinct().as("fancyCnt")
                        )
                );
        Page<ProductListDTO> result =
                new PageImpl<>(dtojpqlQuery.fetch(), pageable, dtojpqlQuery.fetchCount());

        return result;
    }
}