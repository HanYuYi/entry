package com.HanYuYi.servlet.mail;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 用JavaMail发送邮件
 */
public class SendMail {
    private Properties properties = null;
    private String host = "";
    private String address = "";
    private String authCode = "";

    public SendMail() {
        init();
    }

    /**
     * 发送邮件
     * @param mailAddress
     * @param mail
     * @return
     */
    public boolean send(String[] mailAddress, Map<String, String> mail) {
        boolean status = false;
        Session session = mailSession();
        try {
            Transport transport = transport(session);
            // 将收件人转为数组
            List<InternetAddress> addressList = new ArrayList<>();
            for (String item : mailAddress) {
                addressList.add(new InternetAddress(item));
            }
            InternetAddress[] internetAddresses = addressList.toArray(new InternetAddress[mailAddress.length]);

            MimeMessage message = mailPro(session, internetAddresses, mail);
            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            status = true;
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * 组装邮件内容对象
     * @param session
     */
    private MimeMessage mailPro(Session session, InternetAddress[] addresses, Map<String, String> mail) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        // 设置发件人
        message.setFrom(new InternetAddress(address));
        // 设置收件人
        message.setRecipients(Message.RecipientType.TO, addresses);
        message.setSubject(mail.get("title"));
        message.setContent(mail.get("content"), "text/html;charset=utf-8");
        return message;
    }

    /**
     * 获取连接、连接服务器
     * @param session
     * @return
     * @throws MessagingException
     */
    private Transport transport(Session session) throws MessagingException {
        Transport transport = session.getTransport();
        transport.connect(host, address, authCode);
        return transport;
    }

    /**
     * 邮箱认证器：根据发送邮件的邮箱和授权码认证邮箱
     * @return
     */
    private Session mailSession() {
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(address, authCode);
            }
        });
        return session;
    }

    /**
     * 初始化邮箱服务器、协议，需要验证用户名密码
     * @return
     */
    private void init() {
        Properties pro = new Properties();
        InputStream resource = SendMail.class.getClassLoader().getResourceAsStream("mail.properties");
        try {
            pro.load(resource);
            host = pro.getProperty("mail.host");
            address = pro.getProperty("address");
            authCode = pro.getProperty("authCode");
        } catch (IOException e) {
            e.printStackTrace();
        }

        properties = pro;
    }
}
