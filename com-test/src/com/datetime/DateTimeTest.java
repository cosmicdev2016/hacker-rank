package com.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Created by Gaurav Saini on 5/22/2018.
 */
public class DateTimeTest {

    public static void main(String[] args) {

        /**
         * Creating Dates and Times
         */
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());

        LocalDate date1 = LocalDate.of(2015, Month.JANUARY, 20); // Month enum as i/p
        LocalDate date2 = LocalDate.of(2015, 1, 20);

        LocalTime time1 = LocalTime.of(6, 15); // hour and minute
        LocalTime time2 = LocalTime.of(6, 15, 30); // + seconds
        LocalTime time3 = LocalTime.of(6, 15, 30, 200); // + nanoseconds

        LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15);
        LocalDateTime dateTime2 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
        LocalDateTime dateTime3 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30, 200);
        LocalDateTime dateTime4 = LocalDateTime.of(2015, 1, 20, 6, 15);
        LocalDateTime dateTime5 = LocalDateTime.of(2015, 1, 20, 6, 15, 30);
        LocalDateTime dateTime6 = LocalDateTime.of(2015, 1, 20, 6, 15, 30, 200);
        LocalDateTime dateTime7 = LocalDateTime.of(date1, time1); // LocalDate and LocalTime as i/p

        /**
         * Manipulating Dates and Times
         */
        LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
        System.out.println(date); // 2014-01-20
        date = date.plusDays(2);
        System.out.println(date); // 2014-01-22
        date = date.plusWeeks(1);
        System.out.println(date); // 2014-01-29
        date = date.plusMonths(1);
        System.out.println(date); // 2014-02-28
        date = date.plusYears(5);
        System.out.println(date); // 2019-02-28

        LocalTime time = LocalTime.of(5, 15);
        time = time.minusHours(10);
        time = time.minusMinutes(15);
        time = time.minusSeconds(30);
        time = time.minusNanos(200);

        // chained
        date = LocalDate.of(2020, Month.JANUARY, 20);
        time = LocalTime.of(5, 15);
        LocalDateTime dateTime = LocalDateTime.of(date2, time).minusDays(1).minusHours(10).minusSeconds(30);

        /**
         * Methods in LocalDate, LocalTime, and LocalDateTime
         *                        Can call on            Can call on             Can call on
         *                        LocalDate?             LocalTime?              LocalDateTime?
         *
         * plusYears/minusYears       Yes                   No                      Yes
         * plusMonths/minusMonths     Yes                   No                      Yes
         * plusWeeks/minusWeeks       Yes                   No                      Yes
         * plusDays/minusDays         Yes                   No                      Yes
         * plusHours/minusHours       No                    Yes                     Yes
         * plusMinutes/minusMinutes   No                    Yes                     Yes
         * plusSeconds/minusSeconds   No                    Yes                     Yes
         * plusNanos/minusNanos       No                    Yes                     Yes
         */

        /**
         * Working with Periods
         */
        LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2015, Month.MARCH, 30);
        performAnimalEnrichment(start, end);

        /**
         * 1> LocalDate has toEpochDay(), which is the number of days since January 1, 1970.
         * 2> LocalDateTime has toEpochTime(), which is the number of seconds since January
         * 1970.
         * 3> LocalTime does not have an epoch method.
         */
        Period period = Period.ofMonths(1); // create a period
        performAnimalEnrichment(start, end, period);

        Period annually = Period.ofYears(1); // every 1 year
        Period quarterly = Period.ofMonths(3); // every 3 months
        Period everyThreeWeeks = Period.ofWeeks(3); // every 3 weeks
        Period everyOtherDay = Period.ofDays(2); // every 2 days
        Period everyYearAndAWeek = Period.of(1, 0, 7); // every year and 7 days

        // NOTE: You cannot chain methods while creating a Period

        LocalDate date8 = LocalDate.of(2015, 1, 20);
        LocalTime time8 = LocalTime.of(6, 15);
        LocalDateTime dateTime8 = LocalDateTime.of(date8, time8);
        Period period2 = Period.ofMonths(1);
        System.out.println(date8.plus(period2)); // 2015-02-20
        System.out.println(dateTime8.plus(period2)); // 2015-02-20T06:15
        // System.out.println(time7.plus(period2)); // UnsupportedTemporalTypeException

        /**
         * Formatting Dates and Times
         */
        LocalDate date9 = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time9 = LocalTime.of(11, 12, 34);
        LocalDateTime dateTime9 = LocalDateTime.of(date9, time9);
        System.out.println(date9.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(time9.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(dateTime9.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        DateTimeFormatter shortDateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        System.out.println(shortDateTime.format(dateTime9)); // 1/20/20
        System.out.println(shortDateTime.format(date9)); // 1/20/20
        //System.out.println(shortDateTime.format(time9)); // UnsupportedTemporalTypeException

        System.out.println(dateTime9.format(shortDateTime)); // 1/20/20
        System.out.println(date9.format(shortDateTime)); // 1/20/20
        // DateTimeFormatter.ofLocalizedDateTime()
        // DateTimeFormatter.ofLocalizedTime()
        // FormatStyle.SHORT or FormatStyle.MEDIUM

        // Define Custom Pattern
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm");
        System.out.println(dateTime9.format(f)); // January 20, 2020, 11:12

        /**
         * Parsing Dates and Times
         */
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("MM dd yyyy");
        LocalDate date10 = LocalDate.parse("01 02 2015", f2);
        LocalTime time10 = LocalTime.parse("11:22");
        System.out.println(date10); // 2015-01-02
        System.out.println(time10); // 11:22
        LocalDate date11 = LocalDate.parse("2015-01-02");
        System.out.println(date11); // 2015-01-02
    }

    private static void performAnimalEnrichment(LocalDate start, LocalDate end) {
        LocalDate upTo = start;
        while (upTo.isBefore(end)) { // check if still before end
            System.out.println("give new toy: " + upTo);
            upTo = upTo.plusMonths(1); // add a month
        }
    }

    private static void performAnimalEnrichment(LocalDate start, LocalDate end, Period period) {
        LocalDate upTo = start;
        while (upTo.isBefore(end)) { // check if still before end
            System.out.println("give new toy: " + upTo);
            upTo = upTo.plus(period); // adds the period
        }
    }
}
