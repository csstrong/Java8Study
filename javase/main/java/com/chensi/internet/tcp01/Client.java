package com.chensi.internet.tcp01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * @author  chensi
 * @date  2023/1/5
 */
public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("127.0.0.1", 7894);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader bin = new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
		String str = null;
		while ((str = br.readLine()) != null) {
			pw.println(str);
			String result = bin.readLine();
			System.out.println(result);
		}
		s.close();
	}
}
