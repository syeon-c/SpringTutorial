package com.test1.b1.todo.domain;

import com.test1.b1.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tbl_todo")
@EqualsAndHashCode(callSuper=false, of = "id")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tno")
    private Long id;


    private String title;

    private String writer;

    private LocalDate dueDate;

    private boolean complete;

    @Builder
    public Todo(Long id, String title, String writer, boolean complete) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.dueDate = LocalDate.now();
        this.complete = complete;
    }

}
