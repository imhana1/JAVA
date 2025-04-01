package com.example.demo.sample2;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Sample3Controller {
    private List<Product> products = new ArrayList<>();
    @PostConstruct
    public void init() {
        products.add(new Product(1, "삼성", "크리스탈TV", 1000000, 5));
        products.add(new Product(2, "LG", "OLED TV", 5000000, 0));
        products.add(new Product(3, "Apple", "Pro Display XDR", 7900000, 3));
    }

    @GetMapping("/for/list2")
    public ModelAndView list2() {
        return new ModelAndView("sample2/list2").addObject("products", products);
    }
    // 테이블로 products를 출력
    // 재고가 있을 경우 주문 버튼, 재고가 없을 경우 입고 알림 버튼 출력
}
