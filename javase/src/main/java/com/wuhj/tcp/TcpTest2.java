package com.wuhj.tcp;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wuhj
 * @date 2020/12/10 14:54
 */
public class TcpTest2 {
    
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
        
        fis.close();
        os.close();
        socket.close();
        
    
    }
    
    @Test
    public void server() throws IOException {
        ServerSocket ss = new ServerSocket(9090);
    
        Socket socket = ss.accept();
        
        InputStream is = socket.getInputStream();
        
        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\HTWZ\\Desktop\\4.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1){
            fos.write(buffer, 0, len);
        }
        
        fos.close();
        is.close();
        socket.close();
        ss.close();
    }
}
