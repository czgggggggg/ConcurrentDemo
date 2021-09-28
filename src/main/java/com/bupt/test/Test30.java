package com.bupt.test;

import java.util.Hashtable;

/**
 * @Author czgggggggg
 * @Date 2021/9/28
 * @Description
 */
public class Test30 {
    public static void main(String[] args) throws InterruptedException {
        Hashtable<String,String> hashtable = new Hashtable<>();
        new Thread(() -> {
            if(hashtable.get("key") == null)
                hashtable.put("key","value1");
        },"t1").start();
        new Thread(() -> {
            if(hashtable.get("key") == null)
                hashtable.put("key","value2");
        },"t2").start();
        Thread.sleep(1000);
        System.out.println(hashtable.get("key"));
    }
}
