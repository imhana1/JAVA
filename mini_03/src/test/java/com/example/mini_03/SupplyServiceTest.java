package com.example.mini_03;

import com.example.mini_03.supply.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SupplyServiceTest {
  @Autowired
  private SupplyService supplyService;

  // @Test
  public void saveTest() {
    Supply supply = new Supply(null, "연습",null,8);
    supplyService.save(supply);
    System.out.println(supply);
  }

  // @Test
  public void findAllTest() {
    List<Supply> result = supplyService.findAll();
    System.out.println(result);
  }

  // @Test
  public void findBySnoTest() {
    Optional<Supply> result = supplyService.findBySno(5);
    System.out.println(result);
  }

  // @Test
  public void updateIncTest() {
    assertEquals(1, supplyService.updateInc(5));
  }

  // @Test
  public void updateDecTest() {
    assertEquals(1, supplyService.updateDec(5));
  }

  // @Test
  public void deleteTest() {
    assertEquals(1, supplyService.delete(6));
  }

}
