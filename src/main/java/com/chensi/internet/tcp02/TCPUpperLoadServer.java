package com.chensi.internet.tcp02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * @author  chensi
 * @date  2023/1/5
 */
public class TCPUpperLoadServer {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(9876);
		Socket s = ss.accept();
		InputStream is = s.getInputStream();
		BufferedReader brIn = new BufferedReader(new InputStreamReader(is));
		OutputStream os = s.getOutputStream();
		BufferedWriter bwOut = new BufferedWriter(new OutputStreamWriter(os));
		String str = null;
		File file = new File("副本.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		while ((str = brIn.readLine()) != null) {
			System.out.println(str);
			bw.write(str);
			bw.newLine();
			bw.flush();
		}
		bw.close();
		bwOut.write("上传成功。");
		bwOut.flush();
		s.close();
	}
}
