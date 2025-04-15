package com.example.board_2.board2;

import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface CommentDao {
    @SelectKey(statement = "select comments_seq.nextval from dual", keyProperty = "cno", resultType = Integer.class, before = true)
    @Insert("insert into comments(cno, content, nickname,password,write_time, bno) values (#{cno}, #{content}, #{nickname}, #{password}, #{writeTime}, #{bno})")
    int save(Comment comment);

    @Select("select * from comments where bno=#{bno}")
    List<Comment> findByBno (int bno);

    @Delete("delete from comments where bno=#{bno}")
    int deleteByBno(int bno);
}