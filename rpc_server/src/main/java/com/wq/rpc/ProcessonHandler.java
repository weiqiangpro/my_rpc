package com.wq.rpc;

import com.wq.rpc.utils.CloseUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Author: weiqiang
 * Time: 2020/4/4 下午1:42
 */
public class ProcessonHandler implements Runnable {

    private Socket s;
    private Object service;

    public ProcessonHandler(Socket s, Object service) {
        this.s = s;
        this.service = service;
    }

    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;

        try {
            inputStream = new ObjectInputStream(s.getInputStream());

            RpcRequest request = (RpcRequest) inputStream.readObject();
            System.out.println(request);
            Object rs = invoke(request);

            outputStream = new ObjectOutputStream(s.getOutputStream());
            outputStream.writeObject(rs);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            CloseUtil.close(inputStream,outputStream);
        }

    }

    private Object invoke(RpcRequest rpcRequest) {
        try {
            Class<?> aClass = Class.forName(rpcRequest.getClassName());
            Method method = aClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
            return method.invoke(service, rpcRequest.getParames());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
