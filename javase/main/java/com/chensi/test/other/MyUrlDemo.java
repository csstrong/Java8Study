package com.chensi.test.other;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/***********************************
 * @author chensi
 * @date 2022/4/29 16:25
 ***********************************/
public class MyUrlDemo {
    public static void main(String[] args) {
        MyUrlDemo myUrlDemo = new MyUrlDemo();
        try {
            myUrlDemo.showURL();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showURL() throws IOException {

        //第一种，获取类加载的根路径
        File file1 = new File(this.getClass().getResource("/").getPath());
        System.out.println("path1:" + file1);

        //获取当前类所在工程路径，如果不加"/" 获取当前类的加载目录
        File file11 = new File(this.getClass().getResource("").getPath());
        System.out.println("path11:" + file11);

        //第二种，获取项目路径
        File file2 = new File("");
        String canonicalPath = file2.getCanonicalPath();
        System.out.println("path2:" + canonicalPath);

        //第三种
        URL xmlpath = this.getClass().getClassLoader().getResource("");
        System.out.println("path3:" + xmlpath);

        //第四种
        System.out.println("path4:" + System.getProperty("user.dir"));

        //第五种，获取所有的类路径 包括jar包的路径
        System.out.println("path5:" + System.getProperty("java.class.path").split(";")[0]);

        //第六种，获取项目路径
        System.out.println("path6:" + Thread.currentThread().getContextClassLoader().getResource("").getPath());

        //第七种，表示到项目的根目录下，要是想到目录下的子文件夹，修改“/”即可
        //String path7 = request.getSession().getServletContext().getRealPath("/");
        //System.out.println("path7: " + path7);
    }
}
