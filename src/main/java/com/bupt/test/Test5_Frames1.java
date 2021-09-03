package com.bupt.test;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
public class Test5_Frames1 {
    public static void main(String[] args) {
        int reuslt = method1(1);
        System.out.println("result = " + reuslt);
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
