package com.example.mybatis.dao;


import com.example.mybatis.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptDao {
    @Select("select * from dept")
    public List<Dept> findAll();
}
