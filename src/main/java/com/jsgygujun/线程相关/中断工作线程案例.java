package com.jsgygujun.线程相关;

/**
 * 中断线程的方法， 调用Thread.interrupt方法。
 * 被中断的线程：
 *              1. 跑出了InterruptedException异常
 *              2. Thread.currentThread().isInterrupted()标志被置为1
 * @author GuJun
 * @date 2020/11/4
 */
public class 中断工作线程案例 {

    private static class IO密集型工作线程 extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 1000; ++i) {
                    Thread.sleep(1000);
                    System.out.println("IO密集型工作线程 run() " + i);
                }
            } catch (InterruptedException e) {
                // 当外部调用线程对象的interrupt方法时，程序执行到这里。
                e.printStackTrace();
            } finally {
                System.out.println("当前工作线程被中断了，到这里执行清理工作！");
            }
        }
    }

    private static class CPU密集型工作线程 extends Thread {
        @Override
        public void run() {
            int 计次 = 0;
            while (!Thread.currentThread().isInterrupted()) { // 检查线程中断标志，该方法不会清理这个标志
                System.out.println("CPU密集型工作线程 run() " + ++计次);
            }
            System.out.println("当前工作线程接收到了中断信号，退出while循环～");
        }
    }

    private static class CPU且IO密集型工作线程 extends Thread {
        @Override
        public void run() {
            int 计次 = 0;
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    for (int i = 0; i < 1000000 && !Thread.currentThread().isInterrupted(); ++i) {
                        System.out.println("CPU且IO密集型工作线程 run() " + ++计次);
                    }
                    System.out.println("密集型工作检查到当前线程中断标志～");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void 执行中断IO密集型工作线程例子() throws Exception {
        Thread 线程 = new IO密集型工作线程();
        线程.start();
        Thread.sleep(5000);
        线程.interrupt();
        Thread.sleep(3000);
    }

    private void 执行中断CPU密集型工作线程例子() throws Exception {
        Thread 线程 = new CPU密集型工作线程();
        线程.start();
        Thread.sleep(1);
        线程.interrupt();
        Thread.sleep(3000);
    }

    private void 执行中断CPU且IO密集型工作线程例子() throws Exception {
        Thread 线程 = new CPU且IO密集型工作线程();
        线程.start();
        Thread.sleep(1);
        线程.interrupt();
        Thread.sleep(3000);
    }

    public static void main(String[] args) throws Exception {
        中断工作线程案例 实例 = new 中断工作线程案例();
        //实例.执行中断IO密集型工作线程例子();
        //实例.执行中断CPU密集型工作线程例子();
        实例.执行中断CPU且IO密集型工作线程例子();
    }

}
