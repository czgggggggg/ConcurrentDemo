package com.bupt.test;

import java.util.ArrayList;

/**
 * @Author czgggggggg
 * @Date 2021/9/28
 * @Description
 */
public class Test29 {
    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;
    public static void main(String[] args) {
//        ThreadUnsafe threadUnsafe = new ThreadUnsafe();
        ThreadSafeSubClass threadSafeSubClass = new ThreadSafeSubClass();
//        ThreadSafe threadSafe = new ThreadSafe();
        for(int i = 0; i < THREAD_NUMBER; i++){
            new Thread(() -> {
//                threadUnsafe.method1(LOOP_NUMBER);//【001】极有可能会报异常
//                threadSafe.method1(LOOP_NUMBER);//【002】没有问题
                threadSafeSubClass.method1(LOOP_NUMBER);//【003】极有可能会报异常
            },"Thread" + (i + 1)).start();
        }
    }
}
class ThreadUnsafe{
    ArrayList<String> list = new ArrayList<>();
    public void method1(int loop_number){
        for(int i = 0; i < loop_number; i++){
            method2();
            method3();
        }
    }

    private void method2(){
        list.add("1");
    }
    private void method3(){
        list.remove(0);
    }
}
//Exception in thread "Thread1" java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
//	at java.util.ArrayList.rangeCheck(ArrayList.java:653)
//	at java.util.ArrayList.remove(ArrayList.java:492)
//	at com.bupt.test.ThreadUnsafe.method3(Test29.java:36)
//	at com.bupt.test.ThreadUnsafe.method1(Test29.java:28)
//	at com.bupt.test.Test29.lambda$main$0(Test29.java:18)
//	at java.lang.Thread.run(Thread.java:748)

//Exception in thread "Thread2" Exception in thread "Thread1" java.lang.ArrayIndexOutOfBoundsException: -1
//	at java.util.ArrayList.remove(ArrayList.java:501)
//	at com.bupt.test.ThreadUnsafe.method3(Test29.java:36)
//	at com.bupt.test.ThreadUnsafe.method1(Test29.java:28)
//	at com.bupt.test.Test29.lambda$main$0(Test29.java:18)
//	at java.lang.Thread.run(Thread.java:748)
//java.lang.ArrayIndexOutOfBoundsException: -1
//	at java.util.ArrayList.add(ArrayList.java:459)
//	at com.bupt.test.ThreadUnsafe.method2(Test29.java:33)
//	at com.bupt.test.ThreadUnsafe.method1(Test29.java:27)
//	at com.bupt.test.Test29.lambda$main$0(Test29.java:18)
//	at java.lang.Thread.run(Thread.java:748)

class ThreadSafe{
    public void method1(int loop_number){
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < loop_number; i++){
            method2(list);
            method3(list);
        }
    }

//    private void method2(ArrayList list){
//        list.add("1");
//    }
//    private void method3(ArrayList list){
//        list.remove(0);
//    }
    public void method2(ArrayList list){
        list.add("1");
    }
    public void method3(ArrayList list){
        list.remove(0);
    }
}
class ThreadSafeSubClass extends ThreadSafe{
    @Override
    public void method3(ArrayList list) {
        new Thread(() -> {
            list.remove(0);
        }).start();
    }
}
//Exception in thread "Thread-299" java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
//	at java.util.ArrayList.rangeCheck(ArrayList.java:653)
//	at java.util.ArrayList.remove(ArrayList.java:492)
//	at com.bupt.test.ThreadSafeSubClass.lambda$method3$0(Test29.java:89)
//	at java.lang.Thread.run(Thread.java:748)