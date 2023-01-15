package com.test1.b1.todo.repository;

import com.test1.b1.todo.domain.Todo;
import com.test1.b1.todo.dto.TodoSearchDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2

public class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testDelete() {
        Long tno = 1L;

        todoRepository.deleteById(tno);
    }

    @Test
    public void testPaging() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        log.info(result);

        log.info(result.getTotalElements());

        log.info(result.getNumber());
    }

    @Test
    public void testInserts() {
        IntStream.rangeClosed(1, 100).forEach((i) -> {
            Todo todo = Todo.builder()
                    .title("Title1" + i)
                    .writer("user" + i)
                    .dueDate(LocalDate.now())
                    .complete(false)
                    .build();

            log.info(todoRepository.save(todo));
        });
    }

    @Test
    public void testListWithCount() {

        Pageable pageable =
                PageRequest.of(0, 10,
                        Sort.by("id").descending());

        Page<Object[]> result = todoRepository.listWithCount(pageable);
    }

    @Test
    public void testQueryDSL() {

        Pageable pageable =
                PageRequest.of(0, 10,
                        Sort.by("id").descending());

        Page<Todo> result = todoRepository.searchList(pageable, "5");
        log.info(result);

    }

    @Test
    public void testQuerydslWithCount() {

        Pageable pageable =
                PageRequest.of(0, 10,
                        Sort.by("id").descending());

        Page<Object[]> result = todoRepository.searchListWithCount(pageable);
        log.info(result);

    }

    @Test
    public void testQueryWithTodoSearch() {

        Pageable pageable = PageRequest.of(1, 20,
                Sort.by("id").descending());

        TodoSearchDTO searchDTO = TodoSearchDTO.builder()
                .title("14")
                .from(LocalDate.now().plusMonths(1))
                .to(LocalDate.now().plusMonths(1).minusDays(10))
                .build();

        Page<Object[]> result = todoRepository.searchListWithTodoSearch(pageable, searchDTO);
        log.info(result);
    }


}
