package com.wuhj.juc;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData{
    
    volatile int number = 0;
    
    public void addTo60(){
        
        this.number = 60;
    }
    
    //此时number前面加了volatile修饰，不保证原子性
    public void addPlusPlus(){
        number++;
    }
    
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
    
}

/**
 * @author wuhj
 * @date 2021/1/21 9:08
 *
 * 1.验证volatile的可见性
 *      假如 int number = 0; number变量之前没有添加volatile关键字修饰，没有可见性
 *      添加了volatile，可以解决可见性问题
 * 2.验证volatile不保证原子性
 *      原子性：不可分割，完整性，即某个线程正在做业务时，中间不可被加塞或者分割，要么同时成功，要么同时失败
 *
 * 3.why不保证原子性
 *
 * 4.如何解决原子性
 *      加sync
 *      使用juc下的AtomicInteger
 */
public class VolatileDemo {
    
    public static void main(String[] args) {
        MyData myData = new MyData();
    
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for(int j = 1; j <= 1000; j++) {
                  myData.addPlusPlus();
                  myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        
        //需要等待上边20个线程全部计算完成，再用main线程取得最终的计算结果
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally number value: " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t finally atomicInteger value: " + myData.atomicInteger);
    }
    
    //volatile可以保证可见性，及时通知其他线程，主物理内存的值已经被修改
    public static void seeOkByVolatile() {
        MyData myData = new MyData();
        
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t update number value:" + myData.number);
        }, "AAA").start();
        
        while (myData.number == 0){
        
        }
        
        System.out.println(Thread.currentThread().getName() + "\t mission is over,main get number value:" + myData.number);
    }
    
}
