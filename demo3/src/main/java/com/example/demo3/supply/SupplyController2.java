package com.example.demo3.supply;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// 선생님이랑 완성한 controller
@Controller
public class SupplyController2 {
    private List<Supply> supplies = new ArrayList<>();
    private int sno = 1;

    // @PostMapping("/supply/create")
    // 스프링의 모든 기능을 사용하려면 @ModelAttribute 를 사용해야함
    public ModelAndView create(@ModelAttribute Supply supply) {
        supply.setSno(sno++);
        supplies.add(supply);
        return new ModelAndView("redirect:/supply/list");
    }

    // @GetMapping("/supply/list")
    // 비품을 구별하려면 int sno 를 불러와야함
    public ModelAndView readAll() {
        return new ModelAndView("supply/list").addObject("supplies", supplies);
    }
    // error
    public ModelAndView update(@RequestParam int sno) {
        // 향상된 for 사용 → for s in supplies : 파이썬의 for each
        //                 s:${supplies} : 스프링의 for each
        //                 for(s of supplies) : 자바의 for each
        for(Supply s:supplies) { // 타입을 지정해줘야하기에 Supply s:supplies 가 됨
            if(s.getSno().equals(sno)) {
                s.setSno(s.getSno()+1);
                break;
            }
        }
        return new ModelAndView("redirect:/supply/list");
    }

    public ModelAndView delete() {
        // 자바에서 향상된 for 는 원소를 하나씩 꺼내는 것이기에 건너뛰면 에러가 남
        // 그래서 거꾸로 실행한다
        for(Supply s:supplies) {
            if(s.getSno().equals(sno)) {
                supplies.remove(s);
            }
        }
        return new ModelAndView("redirect:/supply/list");
    }
}





