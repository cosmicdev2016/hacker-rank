package com.test1;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Gaurav Saini on 11/24/2017.
 */
public class TestCode1 {

    public static void main(String[] args) {

        String[] arr = {"test", "demo", "admin"};
        String str = "kdfsdkkDEMo@";
        System.out.println(Arrays.stream(arr).anyMatch(str.toLowerCase()::contains));

        String test = "path\\";
        String lastWord = test.substring(test.lastIndexOf("\\") + 1);
        System.out.println(lastWord);

        List<Integer> arr2 = new ArrayList<>();
        arr2.add(1);

        Integer[] intArray = {9, 4, 5, 1, 3, 8, 5, 6, 1, 3};

        Arrays.stream(intArray)
                .filter(e -> e != 3 && e != 6)
                .forEach(e -> System.out.print(e + " "));

        List<Map<String, Object>> list7 = new ArrayList<>();
        Map<String, Object> map3 = new HashMap<>();
        map3.put("grp_id", 1);
        map3.put("foo", 2);
        map3.put("bar", 6);
        list7.add(map3);
        map3 = new HashMap<>();
        map3.put("grp_id", 1);
        map3.put("xxx", 4);
        map3.put("yyy", null);
        list7.add(map3);
        map3 = new HashMap<>();
        map3.put("grp_id", 2);
        map3.put("ran1", 111);
        map3.put("uad", 112);
        list7.add(map3);
        map3 = new HashMap<>();
        map3.put("grp_id", 2);
        map3.put("zzz", 444);
        map3.put("uad", 113);
        list7.add(map3);
        map3 = new HashMap<>();
        map3.put("grp_id", 2);
        map3.put("yyyy", 11);
        map3.put("uad", 101);
        list7.add(map3);
        map3 = new HashMap<>();
        map3.put("grp_id", 2);
        map3.put("tttttt", 33);
        map3.put("uad", 121);
        list7.add(map3);
        map3 = new HashMap<>();
        map3.put("grp_id", 3);
        map3.put("zzz", 33);
        map3.put("qqqqq", 89);
        list7.add(map3);
        map3 = new HashMap<>();
        map3.put("grp_id", 2);
        map3.put("ggggg", 44);
        map3.put("uad", 112);
        list7.add(map3);

        int p112 = 1;
        int p113 = 0;
        int p121 = 0;

        List<Map<String, Object>> list8 = null;
        if (p112 == 1 && p113 == 1 && p121 == 1) {
            //no filtering
        } else {
            Set<Integer> filterList = new HashSet<>();
            if (p112 == 0) {
                filterList.add(112);
            }
            if (p113 == 0) {
                filterList.add(113);
            }
            if (p121 == 0) {
                filterList.add(121);
            }

            list8 = list7.stream()
                    .filter(e -> !((Integer) e.get("grp_id") == 2 && filterList.contains((Integer) e.get("uad"))))
                    .collect(Collectors.toList());
        }
        System.out.println();

        double d = 25.34;
        int i = 2;
        System.out.println(d * 2);
    }

    public static void main2(String[] args) {

        Double d = 1234567890123.0;
        Long l = d.longValue();

        System.out.println(l);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse("07/15/2017", formatter);
        System.out.println(Timestamp.valueOf(date.atStartOfDay()));

        System.out.println(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
        System.out.println(Timestamp.valueOf(LocalDateTime.now()));

        //convert util.date to sql.timestamp without time
        Date utilDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(utilDate);
        Timestamp ts = new Timestamp(utilDate.getTime());
        System.out.println(ts);
    }

    public static void main1(String[] args) {
        System.out.println("Test");

        Float feeAmt = Float.parseFloat("1234567890.12") * 100.0f;
        System.out.println("Float value: " + feeAmt);
        System.out.println(feeAmt.longValue());

        Long amt = Long.parseLong("1234567890.12".replaceAll("[.]", ""));
        System.out.println(amt);

        BigDecimal bd = new BigDecimal("1234567890.12");
        bd = bd.multiply(BigDecimal.valueOf(100));
        System.out.println(bd.longValue());

        Float feeAmt2 = Float.valueOf("999999.99");
        System.out.println("Float value: " + feeAmt2);

        BigDecimal bd2 = new BigDecimal("999999.99");
        System.out.println(bd2.doubleValue());

        Float feeAmt3 = (float) bd2.doubleValue();
        System.out.println(feeAmt3);

        long l1 = 999999999999L;
        double d1 = (double) l1 / 100;
        DecimalFormat formatter = new DecimalFormat("0.00");
        System.out.println(formatter.format(d1));

        String number = "1234567.67";
        double amount = Double.parseDouble(number);
        DecimalFormat df = new DecimalFormat("##,##0.00");

        System.out.println(df.format(amount));
    }
}
