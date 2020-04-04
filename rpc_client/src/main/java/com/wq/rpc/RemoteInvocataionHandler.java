package com.wq.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Author: weiqiang
 * Time: 2020/4/4 下午1:25
 */
public class RemoteInvocataionHandler implements InvocationHandler {
    private String host;
    private int port;

    public RemoteInvocataionHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParames(args);
        request.setTypes(method.getParameterTypes());

        RpcNerTransport rpcNerTransport = new RpcNerTransport(host,port);

        return rpcNerTransport.send(request);
    }
}
