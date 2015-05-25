package com.example.mymodule.app2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MyClass {

    public static ReentrantLock executeLock = new ReentrantLock();

    public static ReentrantLock startLock = new ReentrantLock();
    public static transient int start = 0;
    public static final ExecutorService service = Executors.newCachedThreadPool();

    public static final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(
            1);

    public static void main(String[] args) {

        try {
            System.out.println(System.currentTimeMillis());
            executorService.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    System.out.println(System.currentTimeMillis());
                }
            }, 1, 1, TimeUnit.SECONDS);
            executorService.shutdown();
//            try {
//                for (int i = 0; i < 10; i++) {
//                    Thread.sleep(1000);
//                    if (start == 0) {
//                        service.execute(new Task());
//                    } else {
//                        System.out.println("it is starting");
//                    }
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class Task implements Runnable


    {
//        ReentrantLock startLock;
//
//        Task(ReentrantLock startLock) {
//            this.startLock = startLock;
//        }

        @Override
        public void run() {

            start = 1;
            try {
                if (executeLock.tryLock()) {
                    try {
                        System.out.println("executeLock");
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        executeLock.unlock();
                    }
                } else {
                    System.out.println("unlock");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
//                this.startLock.unlock();
                start = 0;
            }

        }
    }
}
