package com.chensi.internet.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * @author  chensi
 * @date  2023/1/5
 */

/**
 * 接受线程ReceiveThread
 */
class ReceiveThread implements Runnable {

	DatagramSocket ds = null;

	public ReceiveThread(DatagramSocket ds) {
		this.ds = ds;
	}

	@Override
	public void run() {
		while (true) {
			try {
				byte buf[] = new byte[1024];
				//2.创建接受数据报
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				ds.receive(dp);
				//3.显示数据报信息
				System.out.println(dp.getAddress().getHostAddress() + ":" + dp.getPort()
					+ ":" + new String(dp.getData(), 0, dp.getLength()));
			} catch (IOException e) {
				System.out.println("接受线程产生异常。");
			}
		}
	}
}

/**
 * 发送线程SendThread
 */
class SendThread implements Runnable {
	DatagramSocket ds = null;

	public SendThread(DatagramSocket ds) {
		this.ds = ds;
	}

	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		try {
			while ((str = br.readLine()) != null) {
				try {
					//2.创建发送数据包
					DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length,
						InetAddress.getByName("127.0.0.1"), 6544);
					//3.发送数据包
					ds.send(dp);
				} catch (IOException e) {
					System.out.println("发送线程产生异常");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

public class UDPTransferDemo {
	public static void main(String[] args) throws IOException {
		DatagramSocket Send = new DatagramSocket();
		DatagramSocket Receive = new DatagramSocket(6544);
		new Thread(new SendThread(Send)).start();
		new Thread(new ReceiveThread(Receive)).start();
	}
}
