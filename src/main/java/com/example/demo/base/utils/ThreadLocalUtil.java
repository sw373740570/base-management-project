package com.example.demo.base.utils;

public class ThreadLocalUtil {

    private static ThreadLocal localThread = new ThreadLocal();

    public static Object get(){
        return localThread.get();
    }

    public static void set(Object value){
        localThread.set(value);
    }
}
