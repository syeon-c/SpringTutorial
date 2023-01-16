package com.test1.b1.todo.dto;

import com.test1.b1.todo.domain.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoListDTO {

    private Long id;

    private String title;

    private String writer;

    private long replyCount;

    private LocalDate regdate;

    private LocalDate moddate;
}
