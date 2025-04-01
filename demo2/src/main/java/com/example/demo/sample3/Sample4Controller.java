package com.example.demo.sample3;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Sample4Controller {
    private List<Book> books = new ArrayList<>();

    @PostConstruct
    public void init() {
        books.add(new Book(1, "J.K. Rowling", "Harry Potter", 15000, 10));
        books.add(new Book(2, "한강", "소년이 온다", 20000, 0));
        books.add(new Book(3, "김성근", "인생은 순간이다", 18000, 20));
    }

    @GetMapping("/books/blist")
    public ModelAndView listBooks() {
        return new ModelAndView("sample3/blist").addObject("books", books);
    }
}
