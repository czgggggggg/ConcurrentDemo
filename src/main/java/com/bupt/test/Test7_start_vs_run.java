package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test7_start_vs_run")
public class Test7_start_vs_run {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            public void run() {
                log.debug("running...");
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("finish!");
            }
        };
        t1.setName("t1");
//        t1.run();//直接调用run()方法，并没有启动新的线程，而是由主方法的线程在执行。本质上还是单线程，依然是同步而非异步。
        t1.start();

        log.debug("do other things...");
    }
}
