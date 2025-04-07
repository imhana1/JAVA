package com.example.mybatis2;

import com.example.mybatis2.memo.Memo;
import com.example.mybatis2.memo.MemoDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class MemoDaoTest {
    @Autowired
    private MemoDao memoDao;

    // @Test
    public void saveTest() {
        Memo m = Memo.builder().title("토익 접수").content("5월 토익 접수 준비").writer("spring").build();
        int result = memoDao.save(m);
        assertEquals(1, result);
    }

    // @Test
    public void findAllTest() {
        assertEquals(1, memoDao.findAll().size());
    }

    // @Test
    public void findByMnoTest(){
        Optional<Memo> result = memoDao.findByMno(1);
        // Optional 을 전달 받으면 get()으로 객체를 꺼낼 수 있다
        // 단 없으면 NoSuchElementException 발생
//        Memo memo = result.get();
        if (result.isPresent()) {
            Memo memo = result.get();
        }
//        assertNotNull(memoDao.findByMno(1));
//        assertNull(memoDao.findByMno(100));
    }

    // @Test
    public void updateTest() {
        int result = memoDao.update("토익 접수 함?", 1);
        assertEquals(1, result);
        result = memoDao.update("귀찮음", 100);
        assertEquals(0, result);
    }

    // 테스트에서 사용하면 자동 rollback
    @Transactional
    @Test
    public void deleteTest() {
        int result = memoDao.delete(1);
        assertEquals(1, result);
        result = memoDao.delete(100);
        assertEquals(0, result);
    }

}
