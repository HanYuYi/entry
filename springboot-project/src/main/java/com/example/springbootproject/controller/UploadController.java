package com.example.springbootproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传
 */
@Slf4j
@Controller
public class UploadController {

    private static final String OS_SEPARATOR = File.separator;

    @GetMapping("/upload")
    public String toUpload() {
        return "view/upload";
    }

    @PostMapping("/upload")
    public void FormUpload(@RequestParam("username") String names, @RequestPart("avatar") MultipartFile[] files) throws IOException {
        log.info("上传信息：用户名{}，文件{}", names, files);

        if (files.length > 0) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    // 获取原生文件名
                    String originalFilename = file.getOriginalFilename();
                    // 输出到文件夹
                    file.transferTo(new File("."+OS_SEPARATOR + "uploadFIle"+OS_SEPARATOR + names + "_" + originalFilename));
                }
            }
        }
    }

}
