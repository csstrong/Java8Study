package com.chensi.file.encode;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/*
 * @author  chensi
 * @date  2023/2/28
 */
public class FileUtils {

	public static void main(String[] args) {
		String path = "C:\\Users\\lenovo\\Documents\\temp\\rc-upload-1677545353252-9.pdf";
		String base64 = readFile(path);
		System.out.println(base64.substring(0,1000));
	}

	/**
	 * 文件转base64
	 *
	 * @param file
	 * @return
	 */
	private static String fileToBase64(File file) {
		if (!file.exists()) {
			return null;
		}
		byte[] fileBytes;
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			fileBytes = new byte[(int) file.length()];
			inputStream.read(fileBytes);
			inputStream.close();
			return Base64.getEncoder().encodeToString(fileBytes);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * word转pdf
	 *
	 * @param srcPath
	 * @param desPath
	 * @return
	 */
	public static boolean wordToPDF(String srcPath, String desPath) {
		FileOutputStream out = null;
		try {
			if (!new File(srcPath).exists()) {
				return false;
			}
			File outFile = new File(desPath);
			out = new FileOutputStream(outFile);
			Document doc = new Document(srcPath);
			doc.save(out, SaveFormat.PDF);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static String readFile(String path) {
		return fileToBase64(new File(path));
	}
	/**
	 * 获取定长的字符串
	 * @param str 原始字符串
	 * @param len 固定长度
	 * @param c 不够填充的字符
	 * @return 固定长度的字符串
	 */
	public static String getFixedLenString(String str, int len, char c) {
		if (str == null || str.length() == 0){
			str = "";
		}
		if (str.length() == len){
			return str;
		}
		if (str.length() > len){
			return str.substring(0,len);
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() < len){
			sb.append(c);
		}
		return sb.toString();
	}

}
