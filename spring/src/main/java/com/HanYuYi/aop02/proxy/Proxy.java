package com.HanYuYi.aop02.proxy;

/**
 * 代理，相当于中介，求租方租房都找它
 * 它主要可以帮助房东做一些附属的事，比如带房客看房、签合同等等
 */
public class Proxy {
    private Host host;

    public Proxy(){
    }

    public Proxy(Host host){
        this.host = host;
    }

    /**
     * 调用房东的出租方法
     */
    public void rent() {
        seeHouse();
        fare();
        host.rent();
    }

    /**
     * 看房
     */
    public void seeHouse() {
        System.out.println("中介带你看房");
    }

    /**
     * 签租房合同
     */
    public void fare() {
        System.out.println("签合同");
    }
}
