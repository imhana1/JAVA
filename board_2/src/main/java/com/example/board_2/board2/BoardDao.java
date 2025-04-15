package com.example.board_2.board2;

import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface BoardDao {
    @SelectKey(statement = "select boards_seq.nextval from dual", keyProperty = "bno", resultType = Integer.class, before = true)
    @Insert("insert into boards(bno, title, content, nickname, password, write_time) values(#{bno}, #{title}, #{content},#{nickname}, #{password}, #{writeTime})")
    int save(Board board);

    @Select("select * from boards")
    List<Board> findAll();

    @Select("select * from boards where bno=#{bno} and rownum=1")
    Board findByBno (int bno);

    @Update("update boards set read_cnt=read_cnt+1 where bno=#{bno} and rownum=1")
    int increaseReadCnt (int bno);

    @Update("update boards set content=#{content}, title=#{title} where bno=#{bno} and rownum=1")
    int update(Board board);

    @Delete("delete from boards where bno=#{bno} and rownum=1")
    int delete(int bno);
}