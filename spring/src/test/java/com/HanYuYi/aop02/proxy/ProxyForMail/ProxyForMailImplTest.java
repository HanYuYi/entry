package com.HanYuYi.aop02.proxy.ProxyForMail;

import com.HanYuYi.aop02.proxy.Host;
import com.HanYuYi.aop02.proxy.Proxy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProxyForMailImplTest {

    @Test
    void ProxyForMailImpl() {
        Host host = new Host();
        Proxy proxy = new Proxy(host);

        ProxyForMailImpl proxyForMail = new ProxyForMailImpl();
        proxyForMail.setProxy(proxy);
        proxyForMail.rent();
    }

}