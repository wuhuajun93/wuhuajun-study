package com.wuhj.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author wuhj
 * @date 2020/12/2 11:10
 *
 * 一、缓冲区：在Java NIO中负责数据的存取。缓冲区就是数组，用于存储不同数据类型的数据
 *
 * 根据数据类型不同(boolean除外)，提供了相应类型的缓冲区
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * 上述缓冲区的管理方式几乎一致，通过allocate()获取缓冲区
 *
 * 二、缓冲区中存取数据的核心方法：
 * put():存入数据到缓冲区中
 * get():获取缓冲区中数据
 *
 * 三、缓冲区中的四核核心属性
 *  capacity：容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变
 *  limit：界限，表示缓冲区中可以操作数据的大小。(limit 后数据不能进行读写)
 *  position：位置，表示缓冲区中正在操作数据的位置
 *
 *  mark：标记，表示记录当前position的位置，可以通过reset()恢复到mark的位置
 *  position <= limit <= capacity
 *
 *  四、直接缓冲区与非直接缓冲区
 *  非直接缓冲区：通过allocate()方法分配缓冲区，将缓冲区建立在JVM的内存中
 *  直接缓冲区：通过allocateDirect()方法分配直接缓冲区，将缓冲区建立在物理内存中，提高效率
 *
 */
public class TestBuffer {
    
    @Test
    public void test1(){
    
        //1.分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
    
        System.out.println("------------allocate()------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
    
        //2.利用put()存入数据缓冲区
        String str = "abcde";
        buf.put(str.getBytes());
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        
        //3.切换到读取数据模式
        buf.flip();
        System.out.println("------------flip()------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        
        //4.利用get()获取缓冲区中数据
        byte[] bytes = new byte[buf.limit()];
        buf.get(bytes);
        System.out.println(new String(bytes,0,bytes.length));
        System.out.println("------------get()------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        
        //5.rewind() 重复度数据
        buf.rewind();
        System.out.println("------------rewind()------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        
        //6.clear()清空缓冲区,但是缓冲区中数据依然存在，处于被遗忘状态
        buf.clear();
        System.out.println("------------clear()------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
    
        System.out.println((char) buf.get());
    }
}
