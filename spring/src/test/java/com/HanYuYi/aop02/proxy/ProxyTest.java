package com.HanYuYi.aop02.proxy;

import org.junit.jupiter.api.Test;


class ProxyTest {
    /**
     * 测试房客求租房子，只需要跟中介对接就好了
     */
    @Test
    void rent() {
        Host host = new Host();
        Proxy proxy = new Proxy(host);
        proxy.rent();
    }
}