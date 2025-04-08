package com.example.mybatis5;

import com.example.mybatis5.memo.Memo;
import com.example.mybatis5.memo.MemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class MemoServiceTest {
    @Autowired
    private MemoService mServ;

    // @Test
    public void saveTest() {
        Memo memo = new Memo(null, "연습", "연습 연습", "spring", null);
        System.out.println(memo);

        mServ.save(memo);
        System.out.println(memo);
    }

    // @Test
    public void findAllTest(){
        List<Memo> memos = mServ.findAll();
        System.out.println(memos);
        assertNotEquals(0, memos.size());
    }

    // @Test
    public void findByMnoTest() {
        assertEquals(true, mServ.findByMno(2).isPresent());
    }

    // @Transactional
    // test 에 사용할 경우 취소를 의미
    // @Test
    public void updateTest() {
       Memo memo = new Memo(2, "변경", "변경", null, null);
        // 이해하기 쉽게 true, false 로 표시
       assertEquals(true, mServ.update(memo));
    }

    @Transactional
    @Test
    public void deleteTest() {
        assertEquals(true, mServ.delete(2));
    }
}
