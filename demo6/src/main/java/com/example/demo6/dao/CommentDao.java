package com.example.demo6.dao;

import com.example.demo6.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentDao {
    @Select("select * from COMMENTS where PNO=#{pno}")
    List<Comment> findByPno(int pno);
}
