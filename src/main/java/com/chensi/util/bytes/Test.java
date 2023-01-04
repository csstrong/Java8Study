package com.chensi.util.bytes;

/*
 * @author  chensi
 * @date  2022/12/29
 */
public class Test {

	@org.junit.Test
	public void test01() {
		String s = "abcd";
		byte[] b = s.getBytes();
		int i = NumUtil.byte4ToInt(b, 0);
		System.out.println(i);
	}

	@org.junit.Test
	public void test02() {
		String s = "hello";
		byte[] bytes = s.getBytes();
		String string = DataFormatConvertUtil.byteArrayToHexString(bytes);
		byte[] bytes1 = DataFormatConvertUtil.hexStringToBytes(string);
		String s1 = new String(bytes1);
		assert s.equals(s1);

		StringBuilder builder = new StringBuilder();
		for (byte b : bytes1) {
			char c = (char) b;
			builder.append(c);
		}
		assert s.equals(builder.toString());
	}
}
