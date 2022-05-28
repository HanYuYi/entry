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
        // 其实这种方式是在转发
        return "home";
    }

    /**
     * 用 ModelAndView 设置数据和视图
     * @return
     */
    @RequestMapping("home2.do")
    public ModelAndView homePageByModelANdView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "你好，spring-MVC！");
        modelAndView.setViewName("home");
        return modelAndView;
    }

    /**
     * 用 Model 设置数据，且设置请求方式
     * @param model
     * @return
     */
    @RequestMapping(value = "/testRequest", method = {RequestMethod.GET, RequestMethod.POST})
    public String testRequest(Model model) {
        model.addAttribute("requestSuccess", "1");
        return "request";
    }

    /**
     * restful 风格，且接受前端入参（前端单个入参）
     * 从前端接受的参数最好都写上注解，便于区分
     * @param a
     * @param b
     * @param model
     * @return
     */
    @GetMapping("/testRestful/{a}/{b}")
    public String testRestful(@PathVariable int a, @PathVariable int b, @RequestParam("three") int c, Model model) {
        model.addAttribute("restfulCount", "计算结果为：" + (a + b + c));
        return "request";
    }

    /**
     * 限制请求头 和 请求参数
     * @param model
     * @return
     */
    @RequestMapping(path = "/testParams",headers = "Accept-Encoding=gzip, deflate, br", params = "username")
    public String testParams(Model model) {
        model.addAttribute("paramsAccept", "你浏览的的接受压缩格式匹配成功！");
        model.addAttribute("paramsUsername", "username 匹配成功!");
        return "request";
    }

    /**
     * spring mvc 转发 和 重定向
     * @return
     */
    @RequestMapping("/testForward")
    public String forwardAndRedirect(Model model) {
        model.addAttribute("forwardMsg", "转发成功！");
        // 转发
        // return "request";
        // return "/request";

        // 重定向
        return "redirect:/testRequest";
    }

}
