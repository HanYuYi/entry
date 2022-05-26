package com.HanYuYi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
//@RequestMapping("/page")
public class HomeController {

    @RequestMapping("/home.do")
    public String homePage() {
        return "home";
    }

    @RequestMapping("home2.do")
    public ModelAndView homePageByModelANdView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "你好，spring-MVC！");
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/testRequest", method = {RequestMethod.GET, RequestMethod.POST})
    public String testRequest(Model model) {
        model.addAttribute("requestSuccess", "1");
        return "request";
    }

    @GetMapping("/testRestful/{a}/{b}")
    public String testRestful(@PathVariable int a, @PathVariable int b, Model model) {
        model.addAttribute("restfulCount", "计算结果为：" + (a + b));
        return "request";
    }

    @RequestMapping(path = "/testParams",headers = "Accept-Encoding=gzip, deflate, br", params = "username")
    public String testParams(Model model) {
        model.addAttribute("paramsAccept", "你浏览的的接受压缩格式匹配成功！");
        model.addAttribute("paramsUsername", "username 匹配成功!");
        return "request";
    }

}
