package com.bupt.test;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
public class Test6_Frames2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            public void run(){
                method1(10);
            }
        };
        t1.setName("t1");
        t1.start();

        method1(1);
    }
    public static int method1(int x){
        int y = x + 1;
        int result = method2(y);
        return result;
    }
    public static int method2(int x){
        int y = x + 1;
        return y;
    }
}
