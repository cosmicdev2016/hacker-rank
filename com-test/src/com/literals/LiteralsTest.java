package com.literals;

/**
 * Created by Gaurav Saini on 5/9/2018.
 */
public class LiteralsTest {
    LiteralsTest() {}
    LiteralsTest(LiteralsTest t) {
        t1 = t;
    }
    LiteralsTest t1;

    public static void main(String[] args) {
        int i = 10_000;
        System.out.println(i);
        i = 0b000_111;
        System.out.println(i);
        int hex = 0x0a_bc;
        float f = 10_001.1_234f;
        double d = 10_001.1_234;

        //Assignment
        byte l = 127;
        short s = 200;
        float f1 = 1;
        long long1 = 1;
        System.out.println(f1);

        System.out.println('a');
    }
}
