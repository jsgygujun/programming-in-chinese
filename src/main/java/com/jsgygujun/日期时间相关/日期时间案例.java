package com.jsgygujun.日期时间相关;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;
import java.util.Date;

import static java.time.temporal.ChronoField.*;

/**
 * @author GuJun
 * @date 2020/11/13
 */
public class 日期时间案例 {

    public static void main(String[] args) {
        LocalDateTime例子();
        传统日期时间串转LocalDateTime例子();
    }

    public static void LocalDateTime例子() {
        LocalDateTime 现在 = LocalDateTime.now();
        System.out.println(现在); // 2020-11-13T10:09:14.815
        LocalDateTime 明天 = 现在.plusDays(1);
        System.out.println(明天);
        System.out.println(Duration.between(现在, 明天).toHours());
    }

    public static void 传统日期时间串转LocalDateTime例子() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String 传统的日期时间格式串 = dateFormat.format(new Date(System.currentTimeMillis())); // 2020-11-13 10:19:51
        DateTimeFormatter 传统的日期时间格式 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime 日期时间 = LocalDateTime.parse(传统的日期时间格式串, 传统的日期时间格式);
        System.out.println(日期时间);
    }

}
