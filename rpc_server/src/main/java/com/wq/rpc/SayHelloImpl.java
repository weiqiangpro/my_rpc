package com.wq.rpc;

/**
 * Author: weiqiang
 * Time: 2020/4/4 下午1:18
 */
public class SayHelloImpl implements ISayHello {
    public String sayHello(String name) {
            return "hello "+ name;
    }
}
