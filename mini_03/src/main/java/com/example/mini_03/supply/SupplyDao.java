package com.example.mini_03.supply;

import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface SupplyDao {
  @SelectKey(statement = "select supply_seq.nextval from dual", keyProperty = "sno", resultType = Integer.class, before=true)
  // date 안받아
  @Insert("insert into supply(sno, name, quantity) values(#{sno}, #{name}, #{quantity})")
  public int save(Supply supply);

  @Select("select * from supply order by sno desc")
  public List<Supply> findAll();

  @Select("select * from supply where sno=#{sno} and rownum=1")
  public Optional<Supply> findBySno(int sno);

  @Update("update supply set quantity=quantity+1 where sno=#{sno} and rownum=1")
  public int updateInc(int sno);

  @Update("update supply set quantity=quantity-1 where sno=#{sno} and rownum=1")
  public int updateDec(int sno);

  @Delete("delete from supply where sno=#{sno}")
  public int delete(int sno);

}
