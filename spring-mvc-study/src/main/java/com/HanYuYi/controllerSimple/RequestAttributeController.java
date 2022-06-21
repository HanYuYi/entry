package com.HanYuYi.controllerSimple;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求转发演示 @RequestAttribute
 */
@Controller
@RequestMapping("/re")
public class RequestAttributeController {

    @GetMapping("/goTo")
    public String goToPage(HttpServletRequest request) {
        request.setAttribute("msg", "aaaaaaaaa");
        return "forward:/re/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public Map<String, String> success(@RequestAttribute("msg") String msg, HttpServletRequest request) {
        HashMap<String, String> attrMap = new HashMap<>();
        attrMap.put("msg", msg);
        System.out.println(request.getAttribute("msg"));
        return attrMap;
    }

}
