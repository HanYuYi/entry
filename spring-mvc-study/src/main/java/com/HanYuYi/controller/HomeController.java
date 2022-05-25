package com.HanYuYi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/page")
public class HomeController {

    @RequestMapping("/home.do")
    public String homePage() {
        System.out.println("/home.do");
        return "home";
    }

    @RequestMapping("home2.do")
    public ModelAndView homePageByModelANdView() {
        System.out.println("/home2.do");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "你好，spring-MVC！");
        modelAndView.setViewName("home");
        return modelAndView;
    }

}
