package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test22_state")
public class Test22_state {
    public static void main(String[] args) {
        //t1没有start()，不让它运行。   //NEW
        Thread t1 = new Thread(() -> {
            log.debug("running...");
        },"t1");

        //t2在不断的运行    //RUNNABLE
        Thread t2 = new Thread(() -> {
            while(true){

            }
        },"t2");
        t2.start();

        //t3执行完一段代码后立即就结束了（后面主线程会sleep一段时间，能够保证t3先于主线程结束）   //TERMINATED
        Thread t3 = new Thread(() -> {
            log.debug("running...");
        },"t3");
        t3.start();

        //t4是有时间限制的等待   //TIMED_WAITING
        Thread t4 = new Thread(() -> {
            synchronized(Test22_state.class){   //这里加一把类锁对示例6有用。
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t4");
        t4.start();

        //t5是没有时间限制的等待（join没有时间参数），而且t5在等待t2的结束，而t2是一个不会结束的死循环。
        Thread t5 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t5");
        t5.start();

        //t6也要类锁，而同一把类锁已经被t4抢先占有，所以t6会进入阻塞状态。
        Thread t6 = new Thread(() -> {
            synchronized(Test22_state.class){
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t6");
        t6.start();

        log.debug("t1 state : {}", t1.getState());
        log.debug("t2 state : {}", t2.getState());
        log.debug("t3 state : {}", t3.getState());
        log.debug("t4 state : {}", t4.getState());
        log.debug("t5 state : {}", t5.getState());
        log.debug("t6 state : {}", t6.getState());
    }
}

//21:44:38.898 c.Test22_state [main] - t1 state : NEW
//21:44:38.898 c.Test22_state [t3] - running...
//21:44:38.901 c.Test22_state [main] - t2 state : RUNNABLE
//21:44:38.901 c.Test22_state [main] - t3 state : TERMINATED
//21:44:38.901 c.Test22_state [main] - t4 state : TIMED_WAITING
//21:44:38.901 c.Test22_state [main] - t5 state : WAITING
//21:44:38.901 c.Test22_state [main] - t6 state : BLOCKED
