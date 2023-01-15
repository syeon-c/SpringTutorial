package com.test1.b1.todo.domain;

import com.test1.b1.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_todo_reply")
@EqualsAndHashCode(callSuper=false, of = "rno")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne
    private Todo todo;

}
