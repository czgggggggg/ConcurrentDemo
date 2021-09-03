package com.bupt.test;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
public class Test12 {
    public static void main(String[] args) {
        new Thread("t1"){
            public void run(){
                while(true){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
