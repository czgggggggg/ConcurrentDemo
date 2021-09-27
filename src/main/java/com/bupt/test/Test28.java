package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author czgggggggg
 * @Date 2021/9/27
 * @Description
 */
//004
@Slf4j(topic = "c.Test28")
public class Test28 {
    public static void main(String[] args) {
        Number n1 = new Number();
        Number n2 = new Number();
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
            n2.b();
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
//17:27:27.393 c.Test28 [Thread-0] - begin
//17:27:27.393 c.Test28 [Thread-1] - begin
//17:27:27.396 c.Number [Thread-1] - 2
//17:27:28.401 c.Number [Thread-0] - 1

//----------------------------------------------------------------
//003
//@Slf4j(topic = "c.Test28")
//public class Test28 {
//    public static void main(String[] args) {
//        Number n1 = new Number();
//        new Thread(() -> {
//            log.debug("begin");
//            try {
//                n1.a();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(() -> {
//            log.debug("begin");
//            n1.b();
//        }).start();
//        new Thread(() -> {
//            log.debug("begin");
//            n1.c();
//        }).start();
//    }
//}
//@Slf4j(topic = "c.Number")
//class Number{
//    public synchronized void a() throws InterruptedException {
//        Thread.sleep(1000);
//        log.debug("1");
//    }
//    public synchronized void b(){
//        log.debug("2");
//    }
//    public void c(){
//        log.debug("3");
//    }
//}
//17:20:31.173 c.Test28 [Thread-2] - begin
//17:20:31.173 c.Test28 [Thread-0] - begin
//17:20:31.173 c.Test28 [Thread-1] - begin
//17:20:31.176 c.Number [Thread-2] - 3
//17:20:32.186 c.Number [Thread-0] - 1
//17:20:32.186 c.Number [Thread-1] - 2

//17:20:45.826 c.Test28 [Thread-1] - begin
//17:20:45.826 c.Test28 [Thread-0] - begin
//17:20:45.826 c.Test28 [Thread-2] - begin
//17:20:45.828 c.Number [Thread-2] - 3
//17:20:45.828 c.Number [Thread-1] - 2
//17:20:46.831 c.Number [Thread-0] - 1

//17:24:00.114 c.Test28 [Thread-2] - begin
//17:24:00.114 c.Test28 [Thread-1] - begin
//17:24:00.114 c.Test28 [Thread-0] - begin
//17:24:00.117 c.Number [Thread-1] - 2
//17:24:00.117 c.Number [Thread-2] - 3
//17:24:01.121 c.Number [Thread-0] - 1
//----------------------------------------------------------------
//002
//@Slf4j(topic = "c.Test28")
//public class Test28 {
//    public static void main(String[] args) {
//        Number n1 = new Number();
//        new Thread(() -> {
//            log.debug("begin");
//            try {
//                n1.a();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(() -> {
//            log.debug("begin");
//            n1.b();
//        }).start();
//    }
//}
//@Slf4j(topic = "c.Number")
//class Number{
//    public synchronized void a() throws InterruptedException {
//        Thread.sleep(1000);
//        log.debug("1");
//    }
//    public synchronized void b(){
//        log.debug("2");
//    }
//}
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