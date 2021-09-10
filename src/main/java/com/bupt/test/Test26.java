package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author czgggggggg
 * @Date 2021/9/10
 * @Description
 */
@Slf4j(topic = "c.Test26")
public class Test26 {

    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 5000; i++){
                room.increment();
            }
        },"t1");

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 5000; i++){
                room.decrement();
            }
        },"t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("最终number的值为：{}",room.getNumber());
    }
}
class Room{
    public static int number;

    public void increment(){
        synchronized(this){
            number++;
        }
    }

    public void decrement(){
        synchronized(this){
            number--;
        }
    }

    public int getNumber(){
        synchronized(this){
            return number;
        }
    }
}
