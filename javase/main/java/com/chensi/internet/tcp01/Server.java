package com.chensi.internet.tcp01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * @author  chensi
 * @date  2023/1/5
 */
public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(7894);
		Socket s = ss.accept();
		System.out.println(s.getInetAddress() + "已连接。");
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
		String str = null;
		while ((str = br.readLine()) != null) {
			System.out.println(str);
			pw.println(str.toUpperCase());
		}
		s.close();
		ss.close();
	}
}
