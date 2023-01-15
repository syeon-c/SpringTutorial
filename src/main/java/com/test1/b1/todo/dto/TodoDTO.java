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
public class TodoDTO {

    private Long id;

    private String title;

    private String writer;

    private int replyCount;

    private LocalDate regDate;

    private LocalDate moDate;


}
