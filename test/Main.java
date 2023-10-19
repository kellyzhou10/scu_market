package test;

import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;

public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new HttpProcess.MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
