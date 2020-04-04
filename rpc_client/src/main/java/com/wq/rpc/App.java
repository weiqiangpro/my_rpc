package com.wq.rpc;

/**
 * Author: weiqiang
 * Time: 2020/4/4 下午1:21
 */
public class App {
    public static void main(String[] args) {
        RpcProxyClient proxyClient = new RpcProxyClient();
        ISayHello sayHello = proxyClient.clientProxy(ISayHello.class, "localhost", 9999);

        System.out.println(sayHello.sayHello("wei qiang 1111111"));
    }
}
