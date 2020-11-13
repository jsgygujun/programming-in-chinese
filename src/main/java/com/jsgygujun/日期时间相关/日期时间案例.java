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
        LocalDateTime 现在 = LocalDateTime.now();
        System.out.println(现在); // 2020-11-13T10:09:14.815
        LocalDateTime 明天 = 现在.plusDays(1);
        System.out.println(明天);
        System.out.println(Duration.between(现在, 明天).toHours());

        日期时间格式转换();
    }

    public static void 日期时间格式转换() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String 传统的日期时间格式串 = dateFormat.format(new Date(System.currentTimeMillis())); // 2020-11-13 10:19:51
        DateTimeFormatter 传统的日期时间格式 = new DateTimeFormatterBuilder()
                .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .appendLiteral('-')
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral('-')
                .appendValue(DAY_OF_MONTH, 2)
                .appendLiteral(" ")
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR)
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE)
                .toFormatter();
        DateTimeFormatter 传统的日期时间格式2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime 日期时间 = LocalDateTime.parse(传统的日期时间格式串, 传统的日期时间格式);
        System.out.println(日期时间);
        LocalDateTime 日期时间2 = LocalDateTime.parse(传统的日期时间格式串, 传统的日期时间格式2);
        System.out.println(日期时间2);
    }


}
