package com.chensi.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/***********************************
 * @author chensi
 * @date 2022/2/8 10:23
 ***********************************/
public class EchoServer {
    public static void main(String[] args) throws IOException {
        //建立服务器socket
        try (ServerSocket s = new ServerSocket(8189)) {
            //建立客户端的连接
            try (Socket incoming = s.accept()) {
                InputStream inputStream = incoming.getInputStream();
                OutputStream outputStream = incoming.getOutputStream();

                try (Scanner in = new Scanner(inputStream, String.valueOf(StandardCharsets.UTF_8))) {
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream,
                        StandardCharsets.UTF_8), true);
                    out.println("Hello,Enter BYE to exit.");

                    // echo client input
                    Boolean done = false;
                    while (!done && in.hasNextLine()) {
                        String line = in.nextLine();
                        out.println("Echo:" + line);
                        if (line.trim().equals("BYE")) {
                            done = true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    inputStream.close();
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
