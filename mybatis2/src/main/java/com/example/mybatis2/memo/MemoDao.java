package com.example.mybatis2.memo;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemoDao {
    @Insert("insert into memo(mno, title, content, writer) values(memo_seq.nextval, #{title}, #{content}, #{writer})")
    int save(Memo memo);

    @Select("select * from memo")
    List<Memo> findAll();

    // rownum = 1로 결과의 개수를 오라클에게 알려준다
    // Optional : null 이 발생할 수 있는 경우 null 인지 아닌지 (같이 작업하는 사람한테) 알려주는 기능, 미리 처리해주는 기능 (?)
    @Select("select * from memo where mno = #{mno} and rownum = 1")
    Optional<Memo> findByMno(int mno);

    @Update("update memo set content = #{content} where mno = #{mno} and rownum = 1 ")
    int update(String content, int mno);

    @Delete("delete from memo where mno = #{mno} and rownum = 1")
    int delete(int mno);
}
