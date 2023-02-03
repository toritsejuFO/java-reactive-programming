package com.rp.log;

public class Log {
    public static void log(Object message) {
        System.out.print("[" + Thread.currentThread().getName() + "] " + message);
    }

    public static void logLine(Object message) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + message);
    }

    public static void newLine() {
        System.out.println("[" + Thread.currentThread().getName() + "] ");
    }
}
