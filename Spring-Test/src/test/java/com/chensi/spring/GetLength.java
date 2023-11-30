package com.chensi.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * @author jian
 * @version 2021-10-25 19:36
 */
public class GetLength {
    private static final Logger logger = LoggerFactory.getLogger(GetLength.class);

    // 编码方式
    private static final String ENCODE_UTF = "UTF-8";

    private static final String ENCODE_GBK="GBK";

    /**
     * 计算中英文字符串的字节长度 <br/>
     * 一个中文占3个字节
     *
     * @param str
     * @return int 字符串的字节长度
     */
    public static int getLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        try {
            return str.getBytes(ENCODE_GBK).length;
        } catch (UnsupportedEncodingException e) {
            logger.error("计算中英文字符串的字节长度失败，", e);
        }
        return 0;
    }

    /****************************************************************************************
     函 数: CRC16_Checkout
     描 述: CRC16 循环冗余校验算法。
     参 数 一: *puchMsg：需要校验的字符串指针
     参 数 二: usDataLen：要校验的字符串长度
     返 回 值: 返回 CRC16 校验码
     ****************************************************************************************/
    public static int CRC16_Checkout(String message){
        int crc_reg,check;
        crc_reg = 0xFFFF;

        for (int i = 0; i < message.length(); i++) {
            crc_reg = (crc_reg>>8) ^ message.charAt(i);
            for (int j = 0; j < 8; j++) {
                check = crc_reg & 0x0001;
                crc_reg >>= 1;
                if(check==0x0001){
                    crc_reg ^= 0xA001;
                }

            }

        }
        return crc_reg;
    }
    /****************************************************************************************
     函 数: intToHex
     描 述: 十进制转换十六进制器
     参 数 一: n 是十进制数
     返 回 值: 返回十六进制数
     ****************************************************************************************/
    public  static String intToHex(int n) {
        //StringBuffer s = new StringBuffer();
        StringBuilder sb = new StringBuilder(8);
        String a;
        char []b = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(n != 0){
            sb = sb.append(b[n%16]);
            n = n/16;
        }
        a = sb.reverse().toString();
        return a;
    }
    public static short GetCheckCode(byte[] puchMsg) {

        int crc = 0xffff;
        int dxs = 0xa001;
        int hibyte;
        int sbit;
        for (int i = 0; i < puchMsg.length; i++) {
            hibyte = crc >> 8;
            crc = hibyte ^ (char) puchMsg[i];

            for (int j = 0; j < 8; j++) {
                sbit = crc & 0x0001;
                crc = crc >> 1;
                if (sbit == 1) {
                    crc ^= dxs;
                }
            }
        }
        return (short) (crc & 0xffff);
    }

    /**
     * 计算中英文字符串的字节长度
     *
     * @param str
     * @return int
     */
    public static int getStrLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int len = 0;
        for (int i = 0, j = str.length(); i < j; i++) {
            //UTF-8编码格式中文占三个字节，GBK编码格式 中文占两个字节 ;
            len += (str.charAt(i) > 255 ? 3 : 1);
        }
        return len;
    }

    public static String GetCRC_MODBUS(String str) {
        byte[] bytes = toBytes(str);
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;
        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        String crc = Integer.toHexString(CRC);
        return crc.toUpperCase();
    }
    //TODO 将16进制字符串转换为byte[]
    public static byte[] toBytes(String str) {
        byte[] bytes = new BigInteger(str, 16).toByteArray();
        return bytes;
    }


    public static void main(String[] args) {
        //String str9="QN=20231027150156627;ST=21;CN=3889;PW=123456;MN=20231031;Flag=9;CP=&&MnNum=WYLG20230609003&&";
        String str9="QN=20231101130000000;ST=22;CN=2011;PW=123456;MN=cs_site_01;Flag=4;CP=&&DataTime=20231101130000000;a21026-Avg=108.547,a21026-Flag=N;a21005-Avg=1000.581,a21005-Flag=N;&&";
        System.out.println("==========str10===========");

        short c= 0;
        try {
            c = GetCheckCode(str9.getBytes("GBK"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String strCheckCode = String.format("%04x", c);

        System.out.println("c is "+c);
        System.out.println("checkCode is:"+strCheckCode);
        System.out.println("str len is:"+str9.length());

        int length = str9.length();
        String lenStr = String.valueOf(length);
        int len = lenStr.length();
        if(len==1){
            lenStr="000"+lenStr;
        }else if(len==2){
            lenStr="00"+lenStr;
        } else if(len==3){
            lenStr="0"+lenStr;
        }
        try {
            System.out.println("str len size: "+str9.getBytes("GBK").length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int  i9  = CRC16_Checkout(str9);
        System.out.println(i9);
        System.out.println(intToHex(i9));
        System.out.println(getStrLength(str9));

        StringBuilder sb = new StringBuilder();
        sb.append("##")
            .append(lenStr)
            .append(str9)
            .append(intToHex(i9));
        System.out.println(sb);

        System.out.println("==========str10===========");

    }




}
