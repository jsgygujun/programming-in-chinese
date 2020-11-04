package com.jsgygujun.线程相关;

import java.util.concurrent.*;

public class 线程池任务超时案例 {


    private static class 工作任务 implements Runnable {
        public void run() {
            try {
//                for (int i = 0; i < 100000; ++i) {
//                    System.out.println("工作任务 - " + i);
//                    Thread.sleep(1000);
//                }
                Thread.sleep(1000);
                String s = "a:a";
                System.out.println(s.split(":")[3]); // 抛出异常
            } catch (InterruptedException e) {
                System.out.println("刷图任务被中断！");
            }
        }
    }

    private static class 循环执行任务线程 extends Thread {
        private final ExecutorService 线程池;
        private final Runnable 任务;
        private Future<?> 任务句柄;

        public 循环执行任务线程(Runnable 任务) {
            线程池 = Executors.newSingleThreadExecutor();
            this.任务 = 任务;
        }

        @Override
        public void run() {
            int cnt = 0;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    long 开始时间 = System.currentTimeMillis();
                    任务句柄 = 线程池.submit(任务);
                    任务句柄.get(5, TimeUnit.SECONDS);
                    long 结束时间 = System.currentTimeMillis();
                    System.out.printf("任务执行完成！次数：%d, 耗时：%d\n", cnt, (结束时间-开始时间) / 1000);
                } catch (TimeoutException e) {
                    // 工作任务长时间没有返回，则[任务句柄.get]会抛出TimeoutException异常
                    System.out.println("检测任务超时，杀死任务，重启开启新任务~");
                    // 杀死任务
                    任务句柄.cancel(true);
                } catch (ExecutionException e) {
                    // 工作任务抛出任何异常的话，则[任务句柄.get]会抛出ExecutionException异常
                    System.out.println("工作任务抛出任何异常~");
                    break;
                }
                catch (InterruptedException e) {
                    // 当前线程被中断的话
                    System.out.println("循环执行任务线程被中断~");
                    break;
                }
                cnt++;
            }
            任务句柄.cancel(true); // 中断
            线程池.shutdown();
            System.out.println("循环执行任务线程停止~");
        }
    }


    public static void main(String[] args) throws Exception {
        Thread 循环执行任务线程 = new 循环执行任务线程(new 工作任务());
        循环执行任务线程.start();
        Thread.sleep(1000);
        循环执行任务线程.join();
        //循环执行任务线程.interrupt();
    }
}
