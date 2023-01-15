package com.test1.b1.todo.repository.search;

import com.test1.b1.todo.domain.Todo;
import com.test1.b1.todo.dto.TodoSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoSearch {

    // 전체 검색
    Page<Todo> searchList(Pageable pageable, String keyword);
    //
    Page<Object[]> searchListWithCount(Pageable pageable);

    Page<Object[]> searchListWithTodoSearch(Pageable pageable, TodoSearchDTO todoSearch);

//    /** 기간 검색 **/
//    Page<Object[]> searchListWithDateRange(Pageable pageable, String from, String to);
//    /** 완료 여부로 검색 **/
//    Page<Object[]> searchListWithCompleteStatus(Pageable pageable, boolean status);
//    /** 제목 키워드로 검색 **/
//    Page<Object[]> searchListWithKeyword(Pageable pageable, String keyword);
}
