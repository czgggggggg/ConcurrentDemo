package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test4_MultiThread")
public class Test4_MultiThread {
    public static void main(String[] args) {
        new Thread(() -> {
            while(true){
                log.debug("running");
            }
        },"t1").start();

        new Thread(() -> {
            while(true){
                log.debug("running");
            }
        },"t2").start();
    }
}
