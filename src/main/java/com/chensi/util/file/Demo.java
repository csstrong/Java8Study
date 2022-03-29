package com.chensi.util.file;

import com.google.common.io.Files;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
/***********************************
 * @author chensi
 * @date 2022/3/4 14:32
 ***********************************/
public class Demo {
    public static String readConfigFile(String fileName) {
        try {
            String winPath = "src/main/assembly/conf/" + fileName;
            String linuxPath = "./conf/" + fileName;
            String systemInfo = System.getProperty("os.name").toLowerCase();
            String path = systemInfo.contains("linux") ? linuxPath : winPath;
            File file = new File(path);
            String json = "";
            if (!file.exists()) {
                new FileReader(path);
            } else {
                json = Files.toString(file, Charset.forName("UTF-8"));
            }
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error("readConfigFile: " + e.getMessage());
        }
    }

    //写文件
    public static void writeFile(){
        try{
            String data = " This content will append to the end of the file";

            File file =new File("javaio-appendfile.txt");

            //if file doesnt exists, then create it
            if(!file.exists()){
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter(file.getName(),true);
            fileWritter.write(data);
            fileWritter.close();

            System.out.println("Done");

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
