package com.test1.b1.todo.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class TodoSearchDTO {

    private Long id;

    private String title;

    private LocalDate from;

    private LocalDate to;

    private boolean complete;
}
