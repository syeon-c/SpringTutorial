package com.test1.b1.todo.dto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


public class TodoAddDTO {

    @NotEmpty
    private String title;

    @NotEmpty
    private String writer;

    @Future
    private LocalDate dueDate;

}
