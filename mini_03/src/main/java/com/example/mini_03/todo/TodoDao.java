package com.example.mini_03.todo;

import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface TodoDao {
  @Select("select * from todo")
  List<Todo> findAll();

  @SelectKey(statement = "select todo_seq.nextval from dual", keyProperty = "tno", resultType = Integer.class, before = true)
  @Insert("insert into todo(tno, title, memo, deadline) values(#{tno}, #{title}, #{memo}, #{deadline})")
  int save(Todo todo);

  @Select("select * from todo where tno=#{tno} and rownum=1")
  Todo findByTno(int tno);

  @Update("update todo set finish=1 where tno=#{tno} and rownum=1")
  int finish(int tno);

  @Delete("delete from todo where tno=#{tno} and rownum=1")
  int delete(int tno);
}
