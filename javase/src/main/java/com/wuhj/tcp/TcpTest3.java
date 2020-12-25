package com.wuhj.tcp;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wuhj
 * @date 2020/12/10 15:05
 */
public class TcpTest3 {
    
    @Test
    public void client() throws IOException {
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
        
        OutputStream os = socket.getOutputStream();
        
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\HTWZ\\Desktop\\1.jpg"));
        
        byte[] buffer = new byte[1024];
        int len;
        
        while ((len = fis.read(buffer)) != -1){
            os.write(buffer, 0, len);
        }
    
        socket.shutdownOutput();
        
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer2 = new byte[5];
        int len2;
        while ((len2 = is.read(buffer2)) != -1){
            baos.write(buffer2, 0, len2);
        }
        System.out.println(baos.toString());
        
        baos.close();
        is.close();
        fis.close();
        os.close();
        socket.close();
        
        
    }
    
    @Test
    public void server() throws IOException {
        ServerSocket ss = new ServerSocket(9090);
        
        Socket socket = ss.accept();
        
        InputStream is = socket.getInputStream();
        
        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\HTWZ\\Desktop\\5.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1){
            fos.write(buffer, 0, len);
        }
    
        System.out.println("图片传输完成");
    
        OutputStream os = socket.getOutputStream();
        os.write("你好，美女，照片已经收到，非常漂亮！".getBytes());
    
        os.close();
        fos.close();
        is.close();
        socket.close();
        ss.close();
    }
}
