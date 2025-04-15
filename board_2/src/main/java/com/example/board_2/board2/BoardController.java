package com.example.board_2.board2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
    @Autowired
    private BoardService bServ;

    @GetMapping({"/", "/board/list"})
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/board/read")
    public ModelAndView findByBno(@RequestParam Integer bno) {
        return new ModelAndView("board/read").addObject("map", bServ.findByBno(bno));
    }

    @PostMapping("/board/update")
    public ModelAndView update(@ModelAttribute Board board) {
        bServ.update(board);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("board/delete")
    public ModelAndView delete(@RequestParam Integer bno,
                               @RequestParam String password) {
        bServ.delete(bno, password);
        return new ModelAndView("redirect:/");
    }
}

































