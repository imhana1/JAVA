package com.example.demo6;

import com.example.demo6.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService service;

    @Test
    public void mail() {
        String link = "<a href='#'>링크</a>";
        service.sendMail("admin@icia.com", "hasaway@naver.com", "제목이지롱요", link);
    }
}
