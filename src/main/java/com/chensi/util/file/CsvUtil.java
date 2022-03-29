package com.chensi.util.file;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.junit.Test;

/***********************************
 * @author chensi
 * @date 2022/3/7 10:11
 ***********************************/
public class CsvUtil {
    //读取csv文件的内容
    public static List<List<String>> readCsv(String filePath) {
        CsvReader reader = null;
        List<List<String>> result = new ArrayList<>();

        try {
            //window下用gbk,linux下用utf-8
            reader = new CsvReader(filePath, ',', Charset.forName("GBK"));

            //读取标题
            //boolean b = reader.readHeaders();
            //逐条读取记录，直至读完
            while (reader.readRecord()) {

                String s = reader.getRawRecord();
                List<String> list = new ArrayList<>();
                list.add(s);
                result.add(list);
            }
            System.out.println("csv表格中所有行数：" + result.size());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }
        return result;
    }

    //写入csv文件
    public void writeCSV(String path, List<List<String>> strList) {
        String csvFilePath = path;
        CsvWriter csvWriter = null;
        try {
            //创建CSV写对象 例如：CsvWriter（文件路径，分隔符，编码格式）
            csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("GBK"));
            //写内容
            //String[] headers = {"time", "CONC", "distance", "latitude", "longitude"};
            //csvWriter.writeRecord(headers);

            for (List<String> list : strList) {
                String[] writeLine = list.get(0).split(String.valueOf(','));
                System.out.println(Arrays.toString(writeLine));
                csvWriter.writeRecord(writeLine);
            }
            System.out.println("----csv文件已写入-----");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != csvWriter) {
                csvWriter.close();
            }
        }
    }

    @Test
    public void testReadCsv() throws IOException {
        List<List<String>> list = readCsv("src/main/java/com/chensi/util/test.csv");
        System.out.println(list);
    }

    @Test
    public void testWriteCsv() {
        List<List<String>> list = readCsv("src/main/java/com/chensi/util/test.csv");
        writeCSV("src/main/java/com/chensi/util/write.csv", list);
    }
}
