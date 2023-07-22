package com.chensi.netty.socket;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/***********************************
 * @author chensi
 * @date 2022/2/8 9:50
 ***********************************/
public class SocketTest {
    public static void main(String[] args) throws IOException {
        try{
            Socket socket = new Socket("time-a.nist.gov", 13);
            socket.setSoTimeout(1000);
            Scanner in = new Scanner(socket.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
            {
                while(in.hasNext()){
                    String line=in.nextLine();
                    System.out.println(line);
                }
            }
        }catch (SocketTimeoutException socketTimeoutException){
            System.out.println(socketTimeoutException.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void internetAddress() throws UnknownHostException {
        InetAddress byName = InetAddress.getByName("time-a.nist.gov");
        byte[] address = byName.getAddress();
        System.out.println("=================");
        //获取所有主机的地址
        InetAddress[] allByName = InetAddress.getAllByName("baidu.com");
        Arrays.stream(allByName).forEach(System.out::println);

        //得到本地主机的地址
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getAddress());
    }
}
