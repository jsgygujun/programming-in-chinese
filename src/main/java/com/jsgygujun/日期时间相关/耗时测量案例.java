package com.jsgygujun.日期时间相关;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author GuJun
 * @date 2020/11/13
 */
public class 耗时测量案例 {

    private static void 耗时任务() throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
    }

    public static void main(String[] args) throws Exception {
        Instant 计时开始 = Instant.now();
        耗时任务();
        Instant 计时结束 = Instant.now();
        System.out.println(Duration.between(计时开始, 计时结束).toMillis());
    }
}
