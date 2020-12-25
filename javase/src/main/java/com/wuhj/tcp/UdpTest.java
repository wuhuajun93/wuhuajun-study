package com.wuhj.tcp;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author wuhj
 * @date 2020/12/10 15:36
 */
public class UdpTest {
    
    @Test
    public void send() throws IOException {
        
        DatagramSocket socket = new DatagramSocket();
        
        String str = "我是UDP方式发送的导弹";
        byte[] data = str.getBytes();
        InetAddress inet = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(data, 0, data.length, inet, 9090);
        socket.send(packet);
        
        socket.close();
        
    }

    @Test
    public void receiver() throws IOException {
    
        DatagramSocket socket = new DatagramSocket(9090);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
        
        socket.receive(packet);
    
        System.out.println(new String(packet.getData(), 0, packet.getLength()));
        
    }

}
