package com.HanYuYi.aop02.springAop.example01.cache;


/**
 * 第二种方式，使用自定义的类实现切面
 * 没有第一种方式强大
 */
public class DiyCache {

    public void cacheSelectData() {
        System.out.println("查询数据缓存成功");
    }

}
