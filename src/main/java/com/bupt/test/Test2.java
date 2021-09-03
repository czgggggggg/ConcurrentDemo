package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test2")
public class Test2 {
    public static void main(String[] args) {
//        Runnable runnable = new Runnable() {
//            public void run() {
//                log.debug("running");
//            }
//        };

        //方式二：Java8使用lambda简化代码
        Runnable runnable = () -> log.debug("running");

        Thread thread = new Thread(runnable,"t");
        thread.start();
        log.debug("running");


    }
}
