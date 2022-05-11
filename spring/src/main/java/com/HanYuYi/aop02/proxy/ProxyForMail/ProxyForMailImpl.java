package com.HanYuYi.aop02.proxy.ProxyForMail;

import com.HanYuYi.aop02.proxy.Proxy;

/**
 * 加入房东又一个需求：再房客租房成功后，给房东自动发送一封邮件，这个代理可以来做这件事
 * 注意：这里体现了代理模式的精髓：在不改变核心的业务代码上，增加其他的功能，实现了解偶
 */
class ProxyForMailImpl implements ProxyForMail {

    private Proxy proxy;

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    // 调用中介的租房方法
    public void rent() {
        proxy.rent();
        sendMail();
    }

    @Override
    public void sendMail() {
        System.out.println("[DEBUG] 房东收到邮件：你的房子已经出租了");
    }
}
