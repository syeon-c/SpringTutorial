package com.test1.b1.todo.service;

import com.test1.b1.common.dto.PageReqDTO;
import com.test1.b1.common.dto.PageResultDTO;
import com.test1.b1.todo.domain.Todo;
import com.test1.b1.todo.dto.TodoDTO;
import com.test1.b1.todo.dto.TodoListDTO;
import com.test1.b1.todo.dto.TodoSearchDTO;
import com.test1.b1.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    private final ModelMapper modelMapper;

    @Override
    public void add (TodoDTO dto) {

//        Todo entity = getEntity(dto);
//
        Todo entity = modelMapper.map(dto, Todo.class);

        todoRepository.save(entity);

    }
    @Override
    public List<TodoListDTO> getList() {

        log.info("Get List............");

        Pageable pageable = PageRequest.of(1, 10, Sort.by("id").descending());

        Page<Object[]> result = todoRepository.searchListWithCount(pageable);

        List<TodoListDTO> list = result.getContent().stream().map(arr -> {
            Todo entity = (Todo) arr[0];
            TodoListDTO listDTO = modelMapper.map(entity, TodoListDTO.class);

            long count = (long) arr[1];
            listDTO.setReplyCount(count);

            return listDTO;
        }).collect(Collectors.toList());

//        result.getContent().stream()
//                .map(arr -> modelMapper.map(arr, TodoListDTO.class))
//                .collect(Collectors.toList());

        return list;
    }

    @Override
    public List<TodoListDTO> getList(TodoSearchDTO todoSearchDTO) {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        Page<Object[]> result = todoRepository.searchListWithCount(pageable);

        return null;
    }

    @Override
    public PageResultDTO<TodoListDTO> getSearchList() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        Page<Object[]> result = todoRepository.searchListWithCount(pageable);

        List<TodoListDTO> list = result.getContent().stream().map(arr -> {

            Todo entity = (Todo) arr[0];
            long count = (long) arr[1];
            TodoListDTO listDTO = modelMapper.map(entity, TodoListDTO.class);
            listDTO.setReplyCount(count);

            return listDTO;

        }).collect(Collectors.toList());

        PageResultDTO<TodoListDTO> pageResultDTO =
                new PageResultDTO<>(list, pageable, result.getTotalElements(), result.getTotalPages());

        return pageResultDTO;
    }

    @Override
    public PageResultDTO<TodoListDTO> getPageList(int start) {

        PageReqDTO reqDTO = new PageReqDTO();
        reqDTO.setPage(start);

        Pageable pageable = reqDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> result = todoRepository.searchListWithCount(pageable);

        List<TodoListDTO> list = result.getContent().stream().map(arr -> {

            Todo entity = (Todo) arr[0];
            long count = (long) arr[1];
            TodoListDTO listDTO = modelMapper.map(entity, TodoListDTO.class);
            listDTO.setReplyCount(count);

            return listDTO;

        }).collect(Collectors.toList());

        PageResultDTO<TodoListDTO> pageResultDTO =
                new PageResultDTO<>(list, pageable, result.getTotalElements(), result.getTotalPages());

        return pageResultDTO;

    }

    public List<TodoListDTO> getListWithStartPage(int start) {

        Pageable pageable = PageRequest.of(start, 10, Sort.by("id").descending());

        Page<Object[]> result = todoRepository.searchListWithCount(pageable);

        List<TodoListDTO> list = result.getContent().stream().map(arr -> {
            Todo entity = (Todo) arr[0];
            TodoListDTO listDTO = modelMapper.map(entity, TodoListDTO.class);

            long count = (long) arr[1];
            listDTO.setReplyCount(count);

            return listDTO;
        }).collect(Collectors.toList());

        return list;

    }
}
