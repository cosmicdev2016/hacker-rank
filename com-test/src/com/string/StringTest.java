package com.string;

/**
 * Created by Gaurav Saini on 5/22/2018.
 */
public class StringTest {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder(0);
        sb.append("abc");
        System.out.println(sb.capacity());
        sb.append("123456");
        System.out.println(sb.capacity());


        StringBuilder sb1 = new StringBuilder("ABCD");
        /**
         * capacity = 16 + size of the value
         */
        System.out.println(sb1.capacity());
        sb1.append("0123456789012345");
        System.out.println(sb1.capacity());
        sb1.append("abc");
        /**
         * int newCapacity = (oldCapacity + 1) * 2;
         */
        System.out.println(sb1.capacity());

        char a = 10;
    }
}
