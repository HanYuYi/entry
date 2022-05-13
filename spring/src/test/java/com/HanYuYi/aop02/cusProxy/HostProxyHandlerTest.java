package com.HanYuYi.aop02.cusProxy;

import org.junit.jupiter.api.Test;


class HostProxyHandlerTest {
    @Test
    void cusProxyHandler() {
        HostProxyHandler<Host> hip = new HostProxyHandler();
        hip.setTarget(new Host());
        hip.handler("rent");

    }
}