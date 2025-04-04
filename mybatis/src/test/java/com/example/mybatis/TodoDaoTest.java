package com.example.mybatis;

// TodoDao 가 생성되고 동작하는 지를 확인 -> 단위 테스트
// JUnit

import com.example.mybatis.dao.TodoDao;
import com.example.mybatis.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TodoDaoTest {
    @Autowired
    private TodoDao todoDao;

    // @Test
    public void initTest() {
        // assert 문을 사용하지만 junit의 실행 결과는 무조건 성공으로 출력
        assertNotNull(todoDao);
    }

    // @Test
    public void findAllTest(){
        int size = todoDao.findAll().size();
        assertEquals(0, size);
    }

    @Test
    public void saveTest() {
        Todo todo = new Todo(0, "문학구장 가기", LocalDate.of(2025, 4, 5), false);
        int result = todoDao.save(todo);
        assertEquals(1, result);
    }

    // 실패하는 것도 테스트 해라
    // @Test
    public void finishTest() {
        assertEquals(0, todoDao.finish(10));
        assertEquals(1, todoDao.finish(2));
    }

    // 테스트가 끝나고 rollback, 지금같은 삭제가 취소
    @Transactional
    @Test
    public void deleteTest() {
        assertEquals(0, todoDao.delete(10));
        assertEquals(1, todoDao.delete(2));
    }
}
