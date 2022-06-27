package com.HanYuYi.controller;

import com.HanYuYi.pojo.People;
import com.HanYuYi.pojo.Person;
import com.HanYuYi.pojo.Pet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 1、2演示矩阵变量
 * 3、4演示自定义参数参数格式，自定义位于MyConfig
 * 5、内容协商
 */
@Slf4j
@Controller
public class MatrixVariableController {

    // /getMatrixVariableSin/sell;user=zhang;age=20
    @GetMapping("/getMatrixVariableSin/{path}")
    @ResponseBody
    public Map<String, Object> getMatrixVariableSin(@MatrixVariable("user") String u,
                                                    @MatrixVariable("age") Integer a,
                                                    @PathVariable("path") String path) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", u);
        map.put("age", a);
        System.out.println(path);

        return map;
    }

    // 多个相同的路径变了需要 pathVar 指定
    // /getMatrixVariableMore/1;age=46/2;age=13
    @GetMapping("/getMatrixVariableMore/{teacherId}/{studentId}")
    @ResponseBody
    public Map<String, Object> getMatrixVariableMore(@MatrixVariable(value = "age", pathVar = "teacherId") String t,
                                                    @MatrixVariable(value = "age", pathVar = "studentId") Integer s) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("teacherAge", t);
        map.put("studentAge", s);

        return map;
    }


    // 入参：name,color
    @PostMapping("/testConverter")
    public String testConverter(Map map, Pet pet) {
        map.put("pet", pet);

        return "forward:/testConverterSuccess";
    }

    @ResponseBody
    @PostMapping("/testConverterSuccess")
    public Pet testConverterSuccess(HttpServletRequest request) {
        Pet pet = (Pet)request.getAttribute("pet");
        return pet;
    }

    /**
     * 继续客户端 Accept 的内容协商，由于浏览器xml权重高于json，所以在配置了 jackson-dataformat-xml 依赖的情况下会返回xml
     * @return
     */
    @ResponseBody
    @GetMapping("/testAcceptXml")
    public People returnXml() {
        People people = new People("风清扬", 26, new Pet());
        return people;
    }
}
