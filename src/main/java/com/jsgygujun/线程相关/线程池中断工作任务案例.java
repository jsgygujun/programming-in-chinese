package com.jsgygujun.线程相关;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author GuJun
 * @date 2020/11/4
 */
public class 线程池中断工作任务案例 {

    private final ExecutorService 线程池 = Executors.newSingleThreadExecutor();

    private Future<?> f;

    private void 开始执行工作任务(Runnable 任务) {
        f = 线程池.submit(任务);
    }

    private void 停止执行工作任务() {
        f.cancel(true);
    }

    private void 关闭线程池() {
        线程池.shutdown();
    }

    private static class IO密集型工作任务 implements Runnable {
        public void run() {
            try {
                for (int i = 0; i < 100000; ++i) {
                    System.out.println("IO密集型工作任务 run() " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class CPU密集型工作任务 implements Runnable {
        public void run() {
            int 计次 = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("CPU密集型工作任务 run() " + ++计次);
            }
            System.out.println("当前工作线程接收到了中断信号，退出while循环～");
        }
    }


    public static void main(String[] args) throws Exception {
        线程池中断工作任务案例 app = new 线程池中断工作任务案例();
        app.开始执行工作任务(new IO密集型工作任务());
        Thread.sleep(3000);
        app.停止执行工作任务();
        app.开始执行工作任务(new CPU密集型工作任务());
        Thread.sleep(1);
        app.停止执行工作任务();
        app.关闭线程池();
    }

}
