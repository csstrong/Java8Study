package com.chensi.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/***********************************
 * @author chensi
 * @date 2022/2/8 10:13
 ***********************************/
public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        if(args.length>0){
            String host=args[0];
            InetAddress[] addresses = InetAddress.getAllByName(host);
            for(InetAddress a:addresses){
                System.out.println(a);
            }
        }else{
            InetAddress localHostAddress = InetAddress.getLocalHost();
            System.out.println(localHostAddress);
        }
    }
}
