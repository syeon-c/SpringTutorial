package com.test1.b1.product.domain;

import com.test1.b1.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "tbl_product")
@EqualsAndHashCode(callSuper = false, of = "pno")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String pname;

    private int price;

}
