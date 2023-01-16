package com.test1.b1.todo.controller;

import com.test1.b1.common.annotations.JWTAuth;
import com.test1.b1.common.dto.PageResultDTO;
import com.test1.b1.todo.dto.TodoAddDTO;
import com.test1.b1.todo.dto.TodoListDTO;
import com.test1.b1.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos/")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Log4j2
public class TodoController {

    private final TodoService todoService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> add(@Valid @RequestBody TodoAddDTO addDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("has error");
            throw new RuntimeException("binding exception");
        }
        log.info(addDTO);

        return Map.of("result", "success");
    }

    @GetMapping("get1")
    public ResponseEntity<Map<String, String>> get1() {

        Map<String, String> result = Map.of("name", "Hong","country", "chosun");
        return ResponseEntity.ok(result);
    }

    @JWTAuth
    @GetMapping("get2")
    public List<TodoListDTO> get2() {
        return todoService.getList();
    }

    @GetMapping("get3")
    public PageResultDTO<TodoListDTO> get3() {
        return todoService.getSearchList();
    }

    @GetMapping("list")
    public List<TodoListDTO> getList(@RequestParam(defaultValue = "0") Integer start) {

        return todoService.getListWithStartPage(start);
    }


}
