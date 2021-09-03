package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test19_interrupt_park")
public class Test19_interrupt_park {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态 : {}", Thread.currentThread().isInterrupted());
//            log.debug("打断状态 : {}", Thread.interrupted());

//            LockSupport.park();
//            log.debug("unpark...");
        },"t1");

        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}

//没有t1.interrupt();的测试结果：
//（线程一直不结束）
//19:21:14.484 c.Test19_interrupt_park [t1] - park...

//有t1.interrupt();的测试结果：
//19:22:24.417 c.Test19_interrupt_park [t1] - park...
//19:22:25.429 c.Test19_interrupt_park [t1] - unpark...
//19:22:25.429 c.Test19_interrupt_park [t1] - 打断状态 : true

//要注意的是，
//添加22-23行代码后的测试结果：
//（线程再遇到LockSupport.park();就不会再停下来了，也就是打断标记为true的情况下，park会失效）
//19:23:51.457 c.Test19_interrupt_park [t1] - park...
//19:23:52.469 c.Test19_interrupt_park [t1] - unpark...
//19:23:52.469 c.Test19_interrupt_park [t1] - 打断状态 : true
//19:23:52.470 c.Test19_interrupt_park [t1] - unpark...

//此时如果采用Thread.interrupted()，park又会生效，因为Thread.interrupted()清除了打断标记。
//19:27:23.323 c.Test19_interrupt_park [t1] - park...
//19:27:24.328 c.Test19_interrupt_park [t1] - unpark...
//19:27:24.328 c.Test19_interrupt_park [t1] - 打断状态 : true