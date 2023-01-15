package com.test1.b1.product.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_review")
@EqualsAndHashCode(callSuper = false, of = "rno")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne
    private Product product;

    private String text;
}
