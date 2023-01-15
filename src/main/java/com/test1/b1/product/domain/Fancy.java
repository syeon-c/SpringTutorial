package com.test1.b1.product.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_fancy")
@EqualsAndHashCode(callSuper = false, of = "fno")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Fancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    @ManyToOne
    private Product product;

    private String fwriter;
}
