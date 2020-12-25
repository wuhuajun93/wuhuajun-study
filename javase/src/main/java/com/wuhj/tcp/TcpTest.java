package com.wuhj.tcp;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wuhj
 * @date 2020/12/10 14:34
 */
public class TcpTest {
    
    //客户端
    @Test
    public void client() throws IOException {
    
        Socket socket = null;
        OutputStream os = null;
        try {
            //1.创建socket对象，指明服务器端的IP和端口号
            InetAddress inet = InetAddress.getByName("10.39.1.81");
            socket = new Socket(inet, 8899);
            //2.获取一个输入流，用于输出数据
            os = socket.getOutputStream();
            //3.写出数据的操作
            os.write("你好，我是客户端mm".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源的关闭
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){
                socket.close();
            }
            
        }
        
    }
    
    
    //服务端
    @Test
    public void server(){
    
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建服务端的serverSocket,指明自己的端口号
            ss = new ServerSocket(8899);
            //2.调用accept()表示接收来自客户端的socket
            socket = ss.accept();
            //3.获取输入流
            is = socket.getInputStream();
        
        /*byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1){
            String str = new String(buffer, 0, len);
            System.out.println(str);
        }*/
        
            //4.读取输入流中的数据
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[5];
            int len;
            while ((len = is.read(buffer)) != -1){
                baos.write(buffer, 0, len);
            }
            System.out.println(baos.toString());
            System.out.println("收到来自于 " + socket.getInetAddress().getHostAddress() + "的数据");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            if(baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ss != null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            
            
            
        }
    
        
    }
}
