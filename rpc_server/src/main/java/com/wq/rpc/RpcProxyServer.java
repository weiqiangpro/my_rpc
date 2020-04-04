package com.wq.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: weiqiang
 * Time: 2020/4/4 下午1:19
 */
public class RpcProxyServer {

    public void publisher(Object service, int port) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);
            while (true){
                Socket s = ss.accept();
                executorService.submit(new ProcessonHandler(s,service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
