package com.example.mini_03.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
    @Autowired
    private ContactService cServ;

    @GetMapping("/contact/add")
    public ModelAndView save() {
        return new ModelAndView("contact/add");
    }

    @PostMapping("/contact/add")
    public ModelAndView save(@ModelAttribute Contact contact) {
        int cno = cServ.save(contact);
        return new ModelAndView("redirect:/contact/read?cno=" + cno);
    }

    @GetMapping("/contact/list")
    public ModelAndView findAll() {
        return new ModelAndView("contact/list").addObject("contacts", cServ.findAll());
    }

    @GetMapping("/contact/read")
    public ModelAndView findById(@RequestParam Integer cno) {
        Contact contact = cServ.findById(cno);
        if (contact == null)
            return new ModelAndView("redirect:/contact/list");
        return new ModelAndView("contact/read").addObject("contact", contact);
    }

    @PostMapping("/contact/update")
    public ModelAndView update(@ModelAttribute Contact contact) {
        cServ.update(contact);
        return new ModelAndView("redirect:/contact/read?cno=" + contact.getCno());
    }

    @PostMapping("/contact/delete")
    public ModelAndView delete(@RequestParam Integer cno){
        cServ.delete(cno);
        return new ModelAndView("redirect:/contact/list");
    }
}


























