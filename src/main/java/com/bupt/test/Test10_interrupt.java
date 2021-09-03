package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test10_interrupt")
public class Test10_interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1") {
            public void run() {
                log.debug("enter sleep...");
                try {
//                    Thread.sleep(2000);
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    log.debug("being interrupted. wakeup.");
                    e.printStackTrace();
                }
                log.debug("finish sleep.");
            }
        };
        t1.start();

        Thread.sleep(1000);
        log.debug("interrupt...");
        t1.interrupt();
    }
}

//11:47:03.201 c.Test10_interrupt [t1] - enter sleep...
//java.lang.InterruptedException: sleep interrupted
//11:47:04.202 c.Test10_interrupt [main] - interrupt...
//	at java.lang.Thread.sleep(Native Method)
//11:47:04.202 c.Test10_interrupt [t1] - being interrupted. wakeup.
//	at com.bupt.test.Test10_interrupt$1.run(Test10_interrupt.java:17)
//11:47:04.202 c.Test10_interrupt [t1] - finish sleep.