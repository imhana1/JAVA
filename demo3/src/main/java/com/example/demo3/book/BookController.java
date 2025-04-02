package com.example.demo3.book;

import com.example.demo3.supply.SupplyController;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
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
public class BookController {
    private final SupplyController supplyController;
    private List<Book> books = new ArrayList<>();
    private int bno = 1;

    public BookController(SupplyController supplyController) {
        this.supplyController = supplyController;
    }

    @PostConstruct
    public void init() {
        books.add(new Book(1, "인생은 순간이다", "김성근", LocalDate.of(2024, 9, 10), 15));
        books.add(new Book(2, "소년이 온다", "한강", LocalDate.of(2024, 11, 10), 13));
        books.add(new Book(3, "단 한번의 삶", "김영하", LocalDate.of(2025, 2, 15), 12));
    }

    @GetMapping("/book/list")
    public ModelAndView list() {
        return new ModelAndView("book/list").addObject("books", books);
    }

    @PostMapping("/book/add")
    public ModelAndView add (@ModelAttribute Book book) {
        book.setBno(bno ++);
        books.add(book);
        return new ModelAndView("redirect:/book/list");
    }

    @PostMapping("/book/up")
    public ModelAndView up (@RequestParam int bno) {
        for (Book book : books) {
            if (book.getBno() == bno) {
                book.setQuantity(book.getQuantity() + 1);
                break;
            }
        }
        return new ModelAndView("redirect:/book/list");
    }

    @PostMapping("/book/down")
    public ModelAndView down (@RequestParam int bno) {
        for (Book book : books) {
            if (book.getBno() == bno && book.getQuantity() >= 1) {
                book.setQuantity(book.getQuantity() - 1);
                break;
            }
        }
        return new ModelAndView("redirect:/book/list");
    }

    @PostMapping("/book/delete")
    public ModelAndView delete(@RequestParam int bno) {
        for (int i = books.size() -1; i >= 0; i --) {
            if (books.get(i).getBno() == bno){
                books.remove(i);
                break;
            }
        }
        return new ModelAndView("redirect:/book/list");
    }
}






















