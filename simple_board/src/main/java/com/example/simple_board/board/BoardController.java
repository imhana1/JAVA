package com.example.simple_board.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class BoardController {
    @Autowired
    private BoardService bServ;

    @GetMapping("/board/write")
    public ModelAndView findAll() {
        return new ModelAndView("board/write");
    }

    @PostMapping("/board/write")
    public ModelAndView write(Board board) {
        int bno = bServ.save(board);
        return new ModelAndView("redirect:/board/read?bno=" + bno);
    }

    @GetMapping("/")
    public ModelAndView list() {
        List<Board> boards = bServ.findAll();
        return new ModelAndView("board/list").addObject("boards", boards);
    }

    @GetMapping("/board/read")
    public ModelAndView findByBno(int bno) {
        Board board = bServ.findByBno(bno);
        return new ModelAndView("board/read").addObject("board", board);
    }

    @PostMapping("/board/update")
    public ModelAndView update(Board board) {
        boolean result = bServ.update(board);
        if (result)
            return new ModelAndView("redirect:/board/read?bno=" + board.getBno());
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/board/delete")
    public ModelAndView update(Integer bno, String password){
        bServ.delete(bno, password);
        return new ModelAndView("redirect:/");
    }
}































