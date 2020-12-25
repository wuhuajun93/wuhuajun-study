package com.wuhj.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author wuhj
 * @date 2020/12/2 15:28
 *
 * 一、通道(Channel)：用于源节点与目标节点的连接，在Java NIO中负责缓冲区中的数据传输，Channel本身不存储数据，因此需要配合缓冲区进行传输
 *
 * 二、通道的主要实现类
 * java.nio.channels.Channel 接口
 *      --FileChannel
 *      --SocketChannel
 *      --ServerSocketChannel
 *      --DatagramChannel
 *
 * 三、获取通道
 * 1.Java针对支持通道的类提供了getChannel()方法
 *      本地 IO：
 *      FileInputStream/FileOutputStream
 *      RandomAccessFile
 *
 *      网络IO：
 *      Socket
 *      ServerSocket
 *      DatagramSocket
 *
 * 2.在JDK 1.7中的NIO.2针对各个通道提供了静态方法open()
 * 3.在JDK 1.7中的NIO.2的Files工具类的newByteChannel()
 *
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo
 *
 * 五、分散(Scatter)与聚集(gather)
 * 分散读取(Scatting Reads)：将通道中的数据分散到多个缓冲区中
 * 聚集写入(Gathing Writes)：将多个缓冲区中的数据聚集到通道中
 *
 */
public class TestChannel {
    
    @Test
    public void test4() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\HTWZ\\Desktop\\111.txt","rw");
        
        //获取通道
        FileChannel channel = randomAccessFile.getChannel();
        
        //分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);
        
        //分散读取
        ByteBuffer[] byteBuffers = {buf, buf2};
        channel.read(byteBuffers);
        
        for (ByteBuffer byteBuffer : byteBuffers){
            byteBuffer.flip();
        }
    
        System.out.println(new String(byteBuffers[0].array(), 0, byteBuffers[0].limit()));
        System.out.println("-----------------");
        System.out.println(new String(byteBuffers[1].array(), 0, byteBuffers[0].limit()));
        
        //聚集写入
        RandomAccessFile randomAccessFile2 = new RandomAccessFile("C:\\Users\\HTWZ\\Desktop\\222.txt","rw");
        FileChannel channel2 = randomAccessFile2.getChannel();
        channel2.write(byteBuffers);
    }
    
    
    //利用非直接缓冲区进行传输
    @Test
    public void test3() throws IOException {
    
        FileChannel inChannel = FileChannel.open(Paths.get("C:\\Users\\HTWZ\\Desktop\\1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("C:\\Users\\HTWZ\\Desktop\\5.jpg"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
        
        //inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());
        inChannel.close();
        outChannel.close();
    
    }
    
    
    //使用直接缓冲区的方式完成文件的复制(存在内存映射)
    @Test
    public void test2() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("C:\\Users\\HTWZ\\Desktop\\1.jpg"), StandardOpenOption.READ);
    
        FileChannel outChannel = FileChannel.open(Paths.get("C:\\Users\\HTWZ\\Desktop\\3.jpg"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
    
        //内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        
        //直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);
        
        inChannel.close();
        outChannel.close();
    }
    
    
    //利用通道完成图片的复制
    @Test
    public void test1(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("C:\\Users\\HTWZ\\Desktop\\1.jpg");
            fos = new FileOutputStream("C:\\Users\\HTWZ\\Desktop\\2.jpg");
        
            //获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
        
            //分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
        
            //将通道的数据存入缓冲区
            while (inChannel.read(buf) != -1){
                buf.flip();
                //将缓冲区中的数据写入通道中
                outChannel.write(buf);
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(outChannel != null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inChannel != null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    
        
    }
}
