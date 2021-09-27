package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author czgggggggg
 * @Date 2021/9/27
 * @Description
 */
//----------------------------------------------------------------
//002
@Slf4j(topic = "c.Test28")
public class Test28 {
    public static void main(String[] args) {
        Number n1 = new Number();
        new Thread(() -> {
            log.debug("begin");
            try {
                n1.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            log.debug("begin");
            n1.b();
        }).start();
    }
}
@Slf4j(topic = "c.Number")
class Number{
    public synchronized void a() throws InterruptedException {
        Thread.sleep(1000);
        log.debug("1");
    }
    public synchronized void b(){
        log.debug("2");
    }
}
//16:58:18.068 c.Test28 [Thread-0] - begin
//16:58:18.068 c.Test28 [Thread-1] - begin
//16:58:19.080 c.Number [Thread-0] - 1
//16:58:19.080 c.Number [Thread-1] - 2

//16:58:53.777 c.Test28 [Thread-1] - begin
//16:58:53.777 c.Test28 [Thread-0] - begin
//16:58:53.779 c.Number [Thread-1] - 2
//16:58:54.790 c.Number [Thread-0] - 1
//----------------------------------------------------------------
//001
//@Slf4j(topic = "c.Test28")
//public class Test28 {
//    public static void main(String[] args) {
//        Number n1 = new Number();
//        new Thread(() -> {
//            log.debug("begin");
//            n1.a();
//        }).start();
//        new Thread(() -> {
//            log.debug("begin");
//            n1.b();
//        }).start();
//    }
//}
//@Slf4j(topic = "c.Number")
//class Number{
//    public synchronized void a(){
//        log.debug("1");
//    }
//    public synchronized void b(){
//        log.debug("2");
//    }
//}
//16:55:46.036 c.Test28 [Thread-0] - begin
//16:55:46.036 c.Test28 [Thread-1] - begin
//16:55:46.039 c.Number [Thread-0] - 1
//16:55:46.039 c.Number [Thread-1] - 2

//16:56:05.355 c.Test28 [Thread-1] - begin
//16:56:05.355 c.Test28 [Thread-0] - begin
//16:56:05.358 c.Number [Thread-1] - 2
//16:56:05.358 c.Number [Thread-0] - 1