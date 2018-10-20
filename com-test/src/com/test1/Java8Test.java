package com.test1;

//static import java.lang.System.*; // invalid syntax

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Gaurav Saini on 6/5/2018.
 */
public class Java8Test {

    static {
        x = 10;
        System.out.println("inside static-1");
    }

    {
        System.out.println("inside non-static-1");
    }
    static {
        System.out.println("inside static-2");
    }
    Java8Test() {
        System.out.println("inside constructor..");
    }
    {
        //ONLY called when constructor is invoked
        //Otherwise never called
        System.out.println("inside non-static-2");
    }



    static int x;

    public static void main(String[] args) throws Exception {

        Integer.decode("123"); // returns Integer object
        Integer.parseInt("123"); // returns int
        Integer.valueOf("123"); // returns Integer object

        int[] a = new int[0b101];
        int[][] b = {{1},{2}};
        //Arrays.sort(b); // runtime exception
        //double[] dArr = a; // compiler error

        Object d = new Double(3);
        Number num = (Number)d;
        System.out.println(num);

        new Java8Test();

        //int x = 2;
        for (int x = 0; x < 0; x++) ;

        // for (; ; ) ; VALID but a infinite loop. All statements below would be unreachable and gives Compiler error

        for (int x = 1, y = 6; x < 0; System.out.println(x++));

        for (int x = 0; x < 0; x++) {
            System.out.println(1);
            continue;
            // System.out.println(2); // unreachable code
        }

        final int x1;
        x1 = 0;
        final int y1 = 2;
        switch (x1 + y1) {
            /*case x1:
                System.out.println("A");*/ // X1 is final, but not a compile time constant as its given a value later.
            //case y1: // cannot have same case -> y1 and 2 are same
            case 1:
                System.out.println("B");
            default:
                System.out.println("default");
            case y1:
                System.out.println("C");
        }

        int _6;
        int $6;

        double div = 0.0/0.0;
        //Character c = new Character("c"); // compiler error

        int[] arr[] = new int[2][3]; // valid syntax
        System.out.println(arr.length);
        System.out.println(arr[0].length);

        Object[] arrObj[] = new Object[2][2]; // valid syntax

        //arr = {{1, 2}, {3, 4}}; need to use 'new' when reinitializing array.

        String[][] aaaa = new String[5][];
        aaaa[2] = new String[3];

        // throws runtime exception : Class cast
        /*Object[] arrObj2 = new ArrayList().toArray();
        Integer[] intArr = (Integer[]) arrObj2;*/

        // int[] arrNeg = new int[-1]; //NegativeArraySizeException

        Double d1 = 10.3;
        Integer i1 = 2;
        System.out.println(d1 + i1);

        test(10);
    }

    static void test(int x) {
        System.out.println("int called");
    }

    static void test(long x) {
        System.out.println("long called");
    }

    static void test(byte x) {
        System.out.println("byte called");
    }
}
