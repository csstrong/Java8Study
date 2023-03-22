package com.chensi.internet.tcp02;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * @author  chensi
 * @date  2023/1/6
 */
public class TCPUpperLoadClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 9876);
		InputStream is = socket.getInputStream();
		BufferedReader brIn = new BufferedReader(new InputStreamReader(is));
		OutputStream os = socket.getOutputStream();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

		File file = new File("E:\\tmp\\aim.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String str = null;
		while ((str = br.readLine()) != null) {
			bw.write(str);
			//此处换行是为了给服务器端读取一行的结束标志
			bw.newLine();
			bw.flush();
		}
		//没有这句话两端都处于等待状态
		socket.shutdownOutput();
		br.close();
		str = brIn.readLine();
		System.out.println(str);
	}
}
