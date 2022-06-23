package com.HanYuYi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 演示矩阵变量
 */
@RestController
public class MatrixVariableController {

    // /getMatrixVariableSin/sell;user=zhang;age=20
    @GetMapping("/getMatrixVariableSin/{path}")
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
    public Map<String, Object> getMatrixVariableMore(@MatrixVariable(value = "age", pathVar = "teacherId") String t,
                                                    @MatrixVariable(value = "age", pathVar = "studentId") Integer s) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("teacherAge", t);
        map.put("studentAge", s);

        return map;
    }
}
