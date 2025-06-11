package com.example.demo6.dao;

import com.example.demo6.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentDao {
  @Select("select * from comments where pno=#{pno}")
  List<Comment> findByPno(int pno);

  @Insert("insert into COMMENTS(cno, CONTENT, WRITE_TIME, WRITER, pno) values(comments_seq.nextval, #{content}, sysdate, #{writer}, #{pno})")
  int save(int pno, String content, String writer);

  @Delete("delete from COMMENTS where CNO=#{cno} and WRITER=#{loginId}")
  int deleteByCnoAndWriter(int cno, String loginId);
}
