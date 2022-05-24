package com.HanYuYi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/home.do")
    public String homePage() {
        System.out.println("home");
        return "home";
    }

    @RequestMapping("home2.do")
    public ModelAndView homePageByModelANdView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "你好，spring-MVC！");
        modelAndView.setViewName("home");
        return modelAndView;
    }

}
