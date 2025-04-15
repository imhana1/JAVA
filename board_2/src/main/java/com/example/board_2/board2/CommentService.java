package com.example.board_2.board2;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;

@Service
public class CommentService {
    @Autowired
    private CommentDao cDao;

    public boolean save(Comment comment) {
        System.out.println(comment);
        comment.setWriteTime(LocalDateTime.now());
        return cDao.save(comment)>0;
    }
}