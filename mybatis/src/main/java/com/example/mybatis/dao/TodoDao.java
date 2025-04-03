package com.example.mybatis.dao;

// 데이터 베이스 작업하는 클래스는 Data Access Object 또는 Repository
// 스프링에서 DAO 의 어노테이션은 @Repository
// 스프링은 유명한 라이브러리들을 지원하는데 마이바티스는 그렇게 유명하지 않아서 마이바티스가 스프링을 지원했다
// 마이바티스 DAO 는 스프링이 아닌 마이바티스가 객체를 생성해서 스프링빈으로 등록

import com.example.mybatis.entity.Todo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TodoDao {
    @Select("select * from todo")
    public List<Todo> findAll();

    // 마이바티스에서 insert, delete, update 의 결과 값은 "변경된 행의 개수"
    @Insert("insert into todo values (todo_seq.nextval, #{title}, #{regDate}, #{finish})")
    public int save(Todo todo);

    @Update("update todo set finish = 1 where tno = #{tno}")
    public int finish(int tno);

    @Delete("delete from todo where tno = #{tno}")
    public int delete(int tno);

}
