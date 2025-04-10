package com.example.mini_03.supply;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
public class SupplyController {
  @Autowired
  private SupplyService supplyService;

  @GetMapping("/supply/list")
  public ModelAndView findAll() {
    return new ModelAndView("supply/list").addObject("supplies", supplyService.findAll());
  }

  @GetMapping("/supply/read")
  public ModelAndView findBySno(@RequestParam Integer sno) {
    Optional<Supply> supply = supplyService.findBySno(sno);
    if(sno==null)
      return new ModelAndView("redirect:/supply/list");
    // 안되다가 supply.get() 하니까 됨
    return new ModelAndView("supply/read").addObject("supply", supply.get());
  }

  @GetMapping("/supply/add")
  public ModelAndView add() {
    return new ModelAndView("supply/add");
  }

  @PostMapping("/supply/add")
  public ModelAndView add(@ModelAttribute Supply supply) {
    int sno = supplyService.save(supply);
    return new ModelAndView("redirect:/supply/read?sno=" + supply.getSno());
  }

  @PostMapping("/supply/inc")
  public ModelAndView inc(@RequestParam Integer sno) {
    supplyService.updateInc(sno);
    return new ModelAndView("redirect:/supply/read?sno="+sno);
  }

  @PostMapping("/supply/dec")
  public ModelAndView dec(@RequestParam Integer sno) {
    supplyService.updateDec(sno);
    return new ModelAndView("redirect:/supply/read?sno=" + sno);
  }

  @PostMapping("/supply/delete")
  public ModelAndView delete(@RequestParam Integer sno) {
    supplyService.delete(sno);
    return new ModelAndView("redirect:/supply/list");
  }


}
