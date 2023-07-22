package com.chensi.file.character;

import java.io.*;

/*
 * @author  chensi
 * @date  2023/1/6
 */
public class CopyTest {

	public static void main(String[] args) {
		//一次读取一个字符
		CopyTestFileUseRead();
		//使用readLine方法，一次读取一行
		CopyTestFileUseReadLine();
	}

	private static void CopyTestFileUseRead() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		FileReader fr = null;
		FileWriter fw = null;
		try {
			fr = new FileReader("E:\\tmp\\new 1.txt");
			fw = new FileWriter("E:\\tmp\\copy.txt");
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);
			int ch;
			while ((ch = br.read()) != -1) {
				bw.write(ch);
				bw.flush();
			}
		} catch (IOException e) {
			throw new RuntimeException("读写失败。");
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
				}
			}
		}
	}

	private static void CopyTestFileUseReadLine() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		FileReader fr = null;
		FileWriter fw = null;
		try {
			fr = new FileReader("E:\\tmp\\new 1.txt");
			fw = new FileWriter("E:\\tmp\\copy2.txt");
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);
			int ch;
			while ((ch = br.read()) != -1) {
				bw.write(ch);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			throw new RuntimeException("读写失败。");
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
