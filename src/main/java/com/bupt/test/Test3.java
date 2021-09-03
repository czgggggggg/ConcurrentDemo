package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test3")
public class Test3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("running");
                Thread.sleep(3000);
                return 100;
            }
        });

        Thread t1 = new Thread(task,"t1");
        t1.start();
        Integer result = task.get();//get()方法一直阻塞等待task返回结果。
        log.debug("结果是{}",result);
    }
}
