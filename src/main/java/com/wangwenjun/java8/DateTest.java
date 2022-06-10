package com.wangwenjun.java8;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/13 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class DateTest {
    public static void main(String[] args) throws ParseException, InterruptedException {
/*        Date date = new Date(116, 2, 18);
        System.out.println(date);*/

/*        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                for (int x = 0; x < 100; x++) {
                    Date parseDate = null;
                    try {
                        parseDate = sdf.parse("20160505");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(parseDate);
                }
            }).start();
        }*/

//        testLocalDate();
//        testLocalTime();
//        combineLocalDateAndTime();
//        testInstant();
//        testDuration();
//        testPeriod();
//        testDateFormat();
//        testDateParse();
    }

    @Test
    public void testLocalDate() {
        LocalDate localDate = LocalDate.of(2016, 11, 13);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());

        localDate.get(ChronoField.DAY_OF_MONTH);
    }

    @Test
    public void testLocalTime() {
        LocalTime time = LocalTime.now();
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
    }

    @Test
    public void combineLocalDateAndTime() {
        LocalDate localDate = LocalDate.now();
        LocalTime time = LocalTime.now();

        LocalDateTime localDateTime = LocalDateTime.of(localDate, time);
        System.out.println(localDateTime);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:MM:SS");
        String dateStr = now.format(dateTimeFormatter);
        System.out.println(dateStr);
        System.out.println("---------------------");
        System.out.println(now.format(DateTimeFormatter.ISO_DATE));
        System.out.println(now.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(now.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(now.format(DateTimeFormatter.ISO_TIME));
    }

    @Test
    public void testInstant() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(1000L);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }

    @Test
    public void testDuration() {
        LocalTime time = LocalTime.now();
        LocalTime beforeTime = time.minusHours(-1);
        Duration duration = Duration.between(time, beforeTime);
        System.out.println(duration.toHours());
        System.out.println(duration.toMillis());
        System.out.println(duration.toMinutes());
    }

    @Test
    public void testPeriod() {
        Period period = Period.between(LocalDate.of(1996, 2, 10), LocalDate.of(2021, 12, 29));
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(period.getYears());
    }

    @Test
    public void testDateFormat() {
        LocalDate localDate = LocalDate.now();
        String format1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
//        String format2 = localDate.format(DateTimeFormatter.ISO_LOCAL_TIME);
        System.out.println(format1);
//        System.out.println(format2);

        DateTimeFormatter mySelfFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = localDate.format(mySelfFormatter);
        System.out.println(format);
    }

    @Test
    public void testDateParse() {
        String date1 = "20161113";
        LocalDate localDate = LocalDate.parse(date1, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate);

        DateTimeFormatter mySelfFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date2 = "2016-11-13";
        LocalDate localDate2 = LocalDate.parse(date2, mySelfFormatter);
        System.out.println(localDate2);
    }

    /**
     * 计算LocalDateTime  两个时间的时间差
     */
    @Test
    public void computeBetweenLocalDateTime(){
        LocalDate localDate = LocalDate.now();
        LocalTime time = LocalTime.now();

        LocalDateTime localDateTime = LocalDateTime.of(localDate, time);
        System.out.println(localDateTime);

        LocalDateTime now = LocalDateTime.now();
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:MM:SS");
        //String dateStr = now.format(dateTimeFormatter);
        //System.out.println(dateStr);
        LocalDateTime localDateTime1 = now.minusMinutes(5);
        System.out.println(localDateTime1);

        Duration between = Duration.between(localDateTime, now);
        System.out.println(between.toMillis());
    }

    @Test
    public void tr(){
        final LocalDateTime fromDate = LocalDateTime.now();
        final LocalDateTime toDate = LocalDateTime.now().plusHours(56);

        long minutes = ChronoUnit.MINUTES.between(fromDate, toDate);
        long hours = ChronoUnit.HOURS.between(fromDate, toDate);

        System.out.println(minutes);
        System.out.println(hours);
//        3360
//        56
    }

    @Test
    public void tr2(){
        final LocalDateTime fromDate = LocalDateTime.now();
        final LocalDateTime toDate = LocalDateTime.now().plusHours(56);

        final Duration duration = Duration.between(fromDate, toDate);

        final long minuts = duration.toMinutes();
        final long hours = duration.toHours();

        System.out.println(minuts);
        System.out.println(hours);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:MM:SS");
        String dateStr = fromDate.format(dateTimeFormatter);
        System.out.println(dateStr);

//        3360
//        56
    }
}