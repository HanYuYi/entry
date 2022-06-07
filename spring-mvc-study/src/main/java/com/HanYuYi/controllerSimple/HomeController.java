package com.HanYuYi.controllerSimple;

import com.HanYuYi.pojo.User;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
//@RequestMapping("/page")
//@RestController
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
     * restful 风格 get
     * @param a
     * @param b
     * @param model
     * @return
     */
    @GetMapping("/testRestful/{a}/{b}")
    public String testRestfulGet(@PathVariable int a, @PathVariable int b, Model model) {
        model.addAttribute("restfulCount", "计算结果为：" + (a + b));
        return "request";
    }

    /**
     * restful 风格 (入参拼接没有键，以不同的状态请求同一个url)
     * post
     * @param a
     * @param b
     * @param model
     * @return
     */
    @PostMapping("/testRestful/{a}/{b}")
    public String testRestfulPost(@PathVariable("a") Integer a, @PathVariable("b") Integer b, Model model) {
        model.addAttribute("restfulCount", "您新增的值为：a=" + a + ", b=" + b);
        return "request";
    }

    /**
     * 限制请求头 和 请求参数
     * @param modelMap
     * @return
     */
    @RequestMapping(path = "/testParams",headers = "Accept-Encoding=gzip, deflate, br", params = "username")
    public String testParams(ModelMap modelMap) {
        modelMap.addAttribute("paramsAccept", "你浏览的的接受压缩格式匹配成功！");
        modelMap.addAttribute("paramsUsername", "username 匹配成功!");
        return "request";
    }

    /**
     * spring mvc 转发 和 重定向
     * @return
     */
    @RequestMapping("/testForward")
    public String forwardAndRedirect(Map<String, String> map) {
        map.put("forwardMsg", "转发成功！");
        // 转发
        // return "request";
        // return "/request";

        // 重定向
        return "redirect:/testRequest";
    }

    /**
     * 接受前端入参（前端单个入参）
     * 从前端接受的参数最好都写上注解，便于区分
     * @param one
     * @param two
     * @param model
     * @return
     */
    @GetMapping("/testParam")
    public String testParam(@RequestParam("a") Integer one, @RequestParam("b") Integer two, Model model) {
        model.addAttribute("restfulCount", "计算结果为：" + (one + two));
        return "request";
    }

    /**
     * 接收前端对象格式的参数，对象被注入值是通过set注入的，同时测试list
     * @param user
     */
    @RequestMapping(path = "/testParamForObject", method = {RequestMethod.GET, RequestMethod.POST})
    public String testParamForObject(User user) {
        System.out.println(user);
        return "home";
    }

    /**
     * 接收前端时间字符串格式的参数
     * 接收前端时间字符串格式有两种方式，这是第一种，使用 @DateTimeFormat 注解
     * @param date
     * @return
     */
    @RequestMapping("/testParamForDate")
    public String testParamForDate(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        System.out.println(date);
        return "home";
    }

    /**
     * 接收前端时间字符串格式的参数
     * 接收前端时间字符串格式有两种方式，这是第二种，使用 类型转换器，需要写util，配置转换器，推荐使用第一种
     * @param date
     * @return
     */
    @RequestMapping("/testParamForDateConverter")
    public String testParamForDateConverter(Date date) {
        System.out.println(date);
        return "home";
    }

    /**
     * @ResponseBody 和 Jackson 的使用
     * @ResponseBody 作用和 @RestController 一样
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/ResponseBodyAndJackson")
    @ResponseBody
    public String ResponseBodyAndJackson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // 关闭Jackson默认返回时间戳，并自定义返回格式
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(simp);

        List<String> hobby = new ArrayList<>();
        hobby.add("登山");
        hobby.add("跑步");

        List<User> list = new ArrayList<>();
        list.add(new User("张三", "123456", 22, new Date(), hobby));
        list.add(new User("李四", "123qwe", 19, new Date(), hobby));
        list.add(new User("王五", "112233", 31, new Date(), hobby));

        return mapper.writeValueAsString(list);
    }

    /**
     * FastJson 的使用
     * @return
     */
    @GetMapping("/ResponseBodyAndFastJson")
    @ResponseBody
    public String ResponseBodyAndFastJson() {
        List<String> hobby = new ArrayList<>();
        hobby.add("登山");
        hobby.add("跑步");

        List<User> list = new ArrayList<>();
        list.add(new User("张三", "123456", 22, new Date(), hobby));
        list.add(new User("李四", "123qwe", 19, new Date(), hobby));
        list.add(new User("王五", "112233", 31, new Date(), hobby));

        System.out.println(JSON.parseObject(JSON.toJSONString(list), List.class));

        return JSON.toJSONString(list);
    }
}
