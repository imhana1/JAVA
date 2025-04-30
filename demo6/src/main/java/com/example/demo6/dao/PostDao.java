package com.example.demo6.dao;

import com.example.demo6.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface PostDao {
  // SelectKey 의 실행 결과를 save 의 파라미터인 post 에 저장한다 → 서비스에서 post.getPno() 로 시퀀스값에 접근 가능
  // 기본적으로 4개가 필요함 (sql 문, 그 전에는 어떤 상태인지, 어떤 키에 저장할 것인지, 그 키는 무슨 타입인지)
  // rest 는 백은 백, 프론트는 프론트 (작성한 글을 그대로 쏴줌. rest 에서는 이동한다는 의미가 없음)
  @SelectKey(statement = "select posts_seq.nextval from dual", before = true, keyProperty = "pno", resultType = Integer.class)
  @Insert("insert into posts values(#{pno}, #{title}, #{content}, #{writeTime}, #{writer}, #{readCnt}, #{goodCnt}, #{badCnt})")
  int save(Post post);

  @Select("select * from posts order by pno desc offset (#{pageno}-1) * #{pagesize} rows fetch next #{pagesize} rows only")
  List<Post> findAll(int pageno, int pagesize);

}
