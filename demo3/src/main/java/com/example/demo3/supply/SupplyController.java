package com.example.demo3.supply;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SupplyController {
    private List<Supply> supplies = new ArrayList<>();
    private int sno = 4;

    @PostConstruct      // 값을 초기화 하는 어노테이션
    public void init() {
        supplies.add(new Supply(1, "펩시 콜라", LocalDate.of(2025, 3, 25), 4));
        supplies.add(new Supply(2, "막걸리", LocalDate.of(2025, 3, 26), 3));
        supplies.add(new Supply(3, "마우스", LocalDate.of(2025, 3, 26), 2));
    }
    // 비품 목록을 출력하는 메소드와 html 을 작성
    // html 에는 번호, 제품명, 입고일, 개수, 증가 버튼, 감소 버튼 + 부트스트랩

    @GetMapping("/supply/list1")
    public ModelAndView list1() {
        return new ModelAndView("supply/list1").addObject("supplies", supplies);
    }

    // @ModelAttribute : 사용자 입력을 담은 객체를 생성
    // Supply 객체로 사용자 입력을 받아오는데, 모든 값을 다 입력하는 건 아니다 -> 입력하지 않은 필드는 null(int -> Integer)
    // @ModelAttribute 는 기본 생성자로 객체를 생성한 다음, setter 로 값을 넣는다
    @PostMapping("/supply/add")
    public ModelAndView add(@ModelAttribute Supply supply) {
        supply.setSno(sno ++);
        supplies.add(supply);
        return new ModelAndView("redirect:/supply/list1");
    }

    @PostMapping("supply/up")
    public ModelAndView up(@RequestParam int sno){
        for (Supply supply:supplies) {
            if (supply.getSno() == sno) {
                supply.setQuantity(supply.getQuantity() + 1);
                break;
            }
        }
        return new ModelAndView("redirect:/supply/list1");
    }

    @PostMapping("supply/down")
    public ModelAndView down(@RequestParam int sno) {
        for (Supply supply:supplies) {
            if (supply.getSno() == sno && supply.getQuantity() >= 1) {
                supply.setQuantity(supply.getQuantity() - 1);
                break;
            }
        }
        return new ModelAndView("redirect:/supply/list1");
    }

    @PostMapping("/supply/delete")
    public ModelAndView delete(@RequestParam int sno) {
        for (int i = supplies.size() -1; i >= 0; i --) {
            if (supplies.get(i).getSno() == sno){
                supplies.remove(i);
                break;
            }
        }
        return new ModelAndView("redirect:/supply/list1");
    }
}































