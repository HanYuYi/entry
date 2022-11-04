package com.example.springbootproject.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
class DataType {
    private String title;
    private String link;

}

@Controller
public class TempTestData {

    @ResponseBody
    @RequestMapping("/backLineLink")
    public List<DataType> backLineLink() {
        ArrayList<DataType> dataTypes = new ArrayList<>();
        dataTypes.add(new DataType("防掉签下载1", "https://www.baidu.com"));
        dataTypes.add(new DataType("防掉签下载2", "https://www.jd.com"));

        return dataTypes;
    }
}
