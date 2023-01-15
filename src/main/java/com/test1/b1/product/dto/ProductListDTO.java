package com.test1.b1.product.dto;

import lombok.Data;

@Data
public class ProductListDTO {

    private Long pno;

    private String pname;

    private int price;

    private long reviewCnt;

    private long fancyCnt;

}
