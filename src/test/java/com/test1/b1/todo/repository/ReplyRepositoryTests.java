package com.test1.b1.todo.repository;

import com.test1.b1.todo.domain.Reply;
import com.test1.b1.todo.domain.Todo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReplies() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Long todoNum = (long) ((Math.random() * 100) + 1);

            Todo todo = Todo.builder().id(todoNum).build();

            int replyCount = (int) (Math.random() * 5);

            for(int j = 0; j < replyCount; j++) {

                Reply reply = Reply.builder()
                        .todo(todo)
                        .build();

                replyRepository.save(reply);
            }
        });

    }
}
