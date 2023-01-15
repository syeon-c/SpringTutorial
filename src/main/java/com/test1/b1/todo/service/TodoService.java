package com.test1.b1.todo.service;

import com.test1.b1.common.dto.PageResultDTO;
import com.test1.b1.todo.domain.Todo;
import com.test1.b1.todo.dto.TodoDTO;
import com.test1.b1.todo.dto.TodoListDTO;
import com.test1.b1.todo.dto.TodoSearchDTO;

import java.util.List;

public interface TodoService {

    List<TodoListDTO> getList();

    List<TodoListDTO> getList(TodoSearchDTO todoSearchDTO);

    PageResultDTO<TodoListDTO> getSearchList();

    void add(TodoDTO todoDTO);
    default Todo getEntity(TodoDTO todoDTO) {

        Todo todo = Todo.builder()
                .id(todoDTO.getId())
                .title(todoDTO.getTitle())
                .writer(todoDTO.getWriter())
                .build();

        return todo;
    }
}
