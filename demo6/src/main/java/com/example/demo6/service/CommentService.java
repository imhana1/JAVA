package com.example.demo6.service;

import com.example.demo6.dao.CommentDao;
import com.example.demo6.dto.CommentDto;
import com.example.demo6.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    public List<Comment> write(CommentDto.Create dto, String loginId) {
        commentDao.save(dto.getPno(), dto.getContent(), loginId);
        return commentDao.findByPno(dto.getPno());
    }

    public List<Comment> delete(CommentDto.Delete dto, String loginId) {
        commentDao.deleteByCnoAndWriter(dto.getCno(), loginId);
        return commentDao.findByPno(dto.getPno());
    }
}
