package com.wq.rpc;

import com.wq.rpc.utils.CloseUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Author: weiqiang
 * Time: 2020/4/4 下午1:36
 */
public class RpcNerTransport {
    private String host;
    private int port;

    public RpcNerTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Socket newSocket() {
        try {
            return new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object send(RpcRequest rpcRequest) {
        Socket socket = newSocket();
        ObjectOutputStream outputStream = null;
        ObjectInputStream intputStream = null;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcRequest);
            outputStream.flush();

            intputStream = new ObjectInputStream(socket.getInputStream());
            return intputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            CloseUtil.close(intputStream,outputStream,socket);
        }
        return null;
    }
}
