package com.wq.rpc;

/**
 * Author: weiqiang
 * Time: 2020/4/4 下午1:19
 */
public class App {
    public static void main(String[] args) {
        ISayHello sayHello = new SayHelloImpl();
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.publisher(sayHello,9999);
    }
}
