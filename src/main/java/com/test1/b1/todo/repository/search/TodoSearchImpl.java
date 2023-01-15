package com.test1.b1.todo.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.JPQLQuery;
import com.test1.b1.todo.domain.QReply;
import com.test1.b1.todo.domain.QTodo;
import com.test1.b1.todo.domain.Todo;
import com.test1.b1.todo.dto.TodoSearchDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<Todo> searchList(Pageable pageable, String keyword) {
        log.info("================================");
        log.info("Todo Search");

        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

        query.where(todo.title.like(keyword));

        getQuerydsl().applyPagination(pageable, query);

        log.info("==================================");
        log.info(query);

        List<Todo> list = query.fetch();

        long count = query.fetchCount();

        log.info(list);
        log.info(count);


        return null;
    }

    @Override
    public Page<Object[]> searchListWithCount(Pageable pageable) {

        QTodo todo = QTodo.todo;
        QReply reply = QReply.reply;

        JPQLQuery<Todo> query = from(todo)
                .leftJoin(reply)
                .on(reply.todo.eq(todo))
                .groupBy(todo);

        // 페이징 처리
        getQuerydsl().applyPagination(pageable, query);

        log.info("==================================");
        log.info(query);

        JPQLQuery<Tuple> tupleJPQLQuery = query.select(todo, reply.count());
        List<Tuple> tupleList = tupleJPQLQuery.fetch();
        long count = tupleJPQLQuery.fetchCount();

        log.info(tupleList);
        log.info(count);

        List<Object[]> objects = tupleList.stream()
                .map(tuple -> tuple.toArray()).collect(Collectors.toList());

        return new PageImpl<>(objects, pageable, count);
    }


    public Page<Object[]> searchListWithTodoSearch(Pageable pageable, TodoSearchDTO todoSearch) {

        QTodo todo = QTodo.todo;
        QReply reply = QReply.reply;

        JPQLQuery<Todo> query = from(todo).leftJoin(reply);

        getQuerydsl().applyPagination(pageable, query)
                .on(reply.todo.eq(todo))
                .groupBy(todo);

        JPQLQuery<Tuple> tupleJPQLQuery = query.select(todo, reply.count());

        BooleanBuilder builder = new BooleanBuilder();

        if (todoSearch.getId() != null) {
            builder.and(todo.id.eq(todoSearch.getId()));

        }
        if (todoSearch.getTitle() != null) {
            builder.and(todo.title.contains(todoSearch.getTitle()));

        }
        if (todoSearch.getFrom() != null || todoSearch.getTo() != null) {
            builder.and(todo.dueDate.after(todoSearch.getFrom()).and(todo.regDate.before(todoSearch.getTo().atStartOfDay())));
        }
        if (todoSearch.isComplete()) {
            builder.and(todo.complete.eq(todoSearch.isComplete()));
        } else {
            builder.and(todo.complete.eq(todoSearch.isComplete()));
        }

        query.where(builder);

        List<Tuple> tupleList = tupleJPQLQuery.fetch();

        long count = tupleJPQLQuery.fetchCount();

        log.info(tupleList);
        log.info(count);

        List<Object[]> objects = tupleList.stream()
                .map(tuple -> tuple.toArray()).collect(Collectors.toList());

        return new PageImpl<>(objects, pageable, count);

    }
//    @Override
//    public Page<Object[]> searchListWithDateRange(Pageable pageable, String from, String to) {
//
//        LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ISO_DATE);
//        LocalDate toDate = LocalDate.parse(from, DateTimeFormatter.ISO_DATE);
//
//        QTodo todo = QTodo.todo;
//
//        JPQLQuery<Todo> query = from(todo);
//
//        query.where(todo.dueDate.between(fromDate, toDate));
//
//        log.info("==================================");
//        log.info(query);
//
//        List<Todo> list = query.fetch();
//
//        log.info(list);
//
//        return null;
//    }

//    @Override
//    public Page<Object[]> searchListWithCompleteStatus(Pageable pageable, boolean status) {
//        QTodo todo = QTodo.todo;
//
//        JPQLQuery<Todo> query = from(todo);
//
//        query.where(todo.complete.eq(status));
//
//        getQuerydsl().applyPagination(pageable, query);
//
//        log.info("==================================");
//        log.info(query);
//
//        List<Todo> list = query.fetch();
//
//        log.info(list);
//
//        return null;
//    }
}
