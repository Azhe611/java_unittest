package com.mycompany.app;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import java.util.*; 

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void handleClient(Socket client) {
        try {
            InputStream input = client.getInputStream();
            OutputStream output = client.getOutputStream();

            // 读取数据并回应
            byte[] buffer = new byte[1024];
            int bytesRead = input.read(buffer);
            System.out.println("Received: " + new String(buffer, 0, bytesRead));
            output.write("Hello Client".getBytes());

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void listenAndHandle(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            while (true) {
                Socket client = server.accept();
                new Thread(() -> handleClient(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}