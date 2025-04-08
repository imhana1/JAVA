package com.example.mybatis5.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class MemoController {
    @Autowired      // Autowired 추가하지 않는다면 주입되지 않아 값이 null 이라 에러
    private MemoService memoService;

    @GetMapping("/memo/list")
    public ModelAndView findAll() {
        return new ModelAndView("memo/list").addObject("memos", memoService.findAll());
    }

    @GetMapping("/memo/read")
    public ModelAndView findByMno(@RequestParam Integer mno) {
        Optional<Memo> result = memoService.findByMno(mno);
        if(result.isEmpty()) // 비어있다면 리스트로 이동
            return new ModelAndView("redirect:/memo/list");
        // 비어있지 않다면 꺼내서 read 로 이동
        return new ModelAndView("memo/read").addObject("memo", result.get());
    }
    @GetMapping("/memo/write")
    public ModelAndView save() {
        return new ModelAndView("memo/write");
    }

    @PostMapping("/memo/write")
    public ModelAndView save(@ModelAttribute Memo memo) {
        int mno = memoService.save(memo);
        // mno를 같이 가져가야함
        return new ModelAndView("redirect:/memo/read?mno=" + mno);
    }

    @PostMapping("/memo/update")
    public ModelAndView update(@ModelAttribute Memo memo) {
        memoService.update(memo);
        return new ModelAndView("redirect:/memo/read?mno=" + memo.getMno());
    }

    @PostMapping("/memo/delete")
    public ModelAndView delete(@RequestParam Integer mno) {
        memoService.delete(mno);
        return new ModelAndView("redirect:/memo/list");
    }
}