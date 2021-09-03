package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test8_state")
public class Test8_state {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            public void run() {
                log.debug("running");
            }
        };
        System.out.println(t1.getState());//NEW
        t1.start();
//        t1.start();//同一个线程多次调用start()方法会报java.lang.IllegalThreadStateException异常。
        System.out.println(t1.getState());//RUNNABLE
    }
}
