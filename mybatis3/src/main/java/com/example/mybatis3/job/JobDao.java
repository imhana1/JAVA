package com.example.mybatis3.job;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface JobDao {
    @Insert("insert into job(jno, title) values (job_seq.nextval, #{title})")
    public int save(Job job);

    @Select("select * from job")
    public List<Job> findAll();

    @Select("select * from job where jno = #{jno} and rownum = 1")
    public Job findByJno(int jno);

    @Update("update job set title = #{title} where jno = #{jno} and rownum = 1")
    public int update(String title, int jno);

    @Delete("delete from job where jno = #{jno} and rownum = 1")
    public int delete(int jno);
}















