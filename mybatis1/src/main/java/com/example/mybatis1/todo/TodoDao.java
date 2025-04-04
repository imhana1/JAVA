package com.example.mybatis1.todo;

import org.apache.ibatis.annotations.*;

import java.util.*;

// 스프링의 DAO 어노테이션은 @Repository
@Mapper
public interface TodoDao {
  // 정확한 개수를 모르면 List를 리턴 -> 결과가 0개면 비어있는 리스트를 리턴 -> null이 발생하지 않는다
  @Select("select * from todo")
  public List<Todo> findAll();

  // 기본키로 검색 -> 결과의 개수는 0 또는 1 -> 0이면 null, 1이면 Todo 리턴
  @Select("select * from todo where tno=#{tno} order by tno desc")
  public Todo findByTno(int tno);

  @Select("select * from todo where title=#{title}")
  public List<Todo> findByTitle(String title);

  @Insert("insert into todo values(todo_seq.nextval, #{title}, #{regDate}, #{finish})")
  public int save(Todo todo);

  @Update("update todo set finish=1 where tno=#{tno}")
  public int finish(int tno);

  @Delete("delete from todo where tno=#{tno}")
  public int delete(int tno);
}
