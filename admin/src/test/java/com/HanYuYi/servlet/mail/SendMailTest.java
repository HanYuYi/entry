package com.HanYuYi.servlet.mail;

import org.junit.jupiter.api.Test;

import java.util.HashMap;


class SendMailTest {

    @Test
    void send() {
        SendMail sendMail = new SendMail();
        String[] arr = { "marvinhan884@gmail.com" };
        HashMap<String, String> mail = new HashMap<>();
        mail.put("title", "这是一封测试邮件");
        mail.put("content", "哈哈哈哈，邮件发送成功！");
        boolean isSuccess = sendMail.send(arr, mail);
        System.out.println(isSuccess);
    }
}