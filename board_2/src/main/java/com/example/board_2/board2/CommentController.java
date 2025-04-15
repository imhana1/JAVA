package com.example.board_2.board2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {
    @Autowired
    private CommentService cServ;

    @PostMapping("/comment/write")
    public ModelAndView commentWrite(@ModelAttribute Comment comment){
        cServ.save(comment);
        return new ModelAndView("redirect:/");
    }
}
