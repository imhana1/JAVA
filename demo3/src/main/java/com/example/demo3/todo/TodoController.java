package com.example.demo3.todo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {
    private List<Todo> todos = new ArrayList<>();
    private int tno = 1;

    // <input nate='title'>이 요청 객체에 담겨 백엔드로 전달되면 같은 이름의 파라미터에 담는다
    // @RequestParam : 요청 객체에서 이름이 같은 데이터를 추출해 파라미터에 담아라
    //                 스프링은 그 어떤 데이터라도 파라미터로 변환할 수 있을까?
    //                 <input type = 'date'> -> 2020-11-20 -> 2020년 11월 20일 객체로 변환이 될까? 안됨.
    @PostMapping("/todo/write")
    public ModelAndView write(@RequestParam String title) {
        Todo todo = new Todo(tno++, title);
        todos.add(todo);
        return new ModelAndView("redirect:/todo/list");
    }

    // R - 목록
    @GetMapping("/todo/list")
    public ModelAndView list(){
        return new ModelAndView("/todo/list").addObject("todos", todos);
    }

    @PostMapping("/todo/finish")
    public ModelAndView finish(@RequestParam int tno) {
        for(Todo todo:todos) {
            if (todo.getTno() == tno) {
                todo.setFinish(true);
                break;
            }
        }
        return new ModelAndView("redirect:/todo/list");
    }

    @PostMapping("/todo/delete")
    public ModelAndView delete(@RequestParam int tno) {
        System.out.println(tno);
        // 0번째 원소부터 차례대로 찾으면서 삭제할 경우, 접근하지 않고 통과하는 원소가 있다
        // 인덱스 : 0 1 2 3
        // 값      A B C D
        // B 를 찾아서 삭제할 경우 -> B가 지워지는 순간에 C가 첫번째, D가 두번째 원소가 된다
        //                      -> 인덱스는 1에서 2로 증가 -> C 를 건너 뛰고 D 로 접근 -> 자바가 용납하지 않는다
        // 자바는 list 에서 원소를 찾아서 삭제 할때는 거꾸로 돌려서 찾는다
        for(int i = todos.size() -1; i >= 0; i --) {
            if (todos.get(i).getTno() == tno) {
                todos.remove(i);
                break;
            }
        }
        return new ModelAndView("redirect:/todo/list");
    }

}





















