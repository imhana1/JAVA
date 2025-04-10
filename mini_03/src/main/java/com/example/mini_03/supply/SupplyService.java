package com.example.mini_03.supply;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class SupplyService {
  @Autowired
  private SupplyDao suppylyDao;
  @Autowired
  private SupplyDao supplyDao;

  public int save(Supply supply) {
    suppylyDao.save(supply);
    return supply.getSno();
  }

  public List<Supply> findAll() {
    return supplyDao.findAll();
  }

  public Optional<Supply> findBySno(Integer sno) {
    return supplyDao.findBySno(sno);
  }

  public int updateInc(Integer sno) {
    return supplyDao.updateInc(sno);
  }

  public int updateDec(Integer sno) {
    return supplyDao.updateDec(sno);
  }

  public int delete(Integer sno) {
    return supplyDao.delete(sno);
  }
}
