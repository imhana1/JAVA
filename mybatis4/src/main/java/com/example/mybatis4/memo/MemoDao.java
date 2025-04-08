package com.example.mybatis4.memo;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemoDao {
    @SelectKey(statement = "select memo_seq.nextval from dual", keyProperty = "mno", resultType = Integer.class, before = true)
    @Insert("insert into memo(mno, title, content, writer) values(#{mno}, #{title}, #{content}, #{writer})")
    public int save(Memo memo);

    @Select("select * from memo")
    public List<Memo> findAll();

    @Select("select * from memo where mno = #{mno} and rownum = 1")
    public Optional<Memo> findByMno(int mno);

    @Update("update memo set title = #{title}, content = #{content} where mno = #{mno} and rownum = 1")
    public int update(Memo memo);

    @Delete("delete from memo where mno = #{mno} and rownum = 1")
    public int delete(int mno);
}
