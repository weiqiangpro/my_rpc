package com.wq.rpc;

import java.lang.reflect.Proxy;

/**
 * Author: weiqiang
 * Time: 2020/4/4 下午1:23
 */
public class RpcProxyClient {

    @SuppressWarnings("all")
    public <T> T clientProxy(final Class<T> interfaceClass, final String host, final int port) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class[]{interfaceClass}, new RemoteInvocataionHandler(host,port));
    }
}
