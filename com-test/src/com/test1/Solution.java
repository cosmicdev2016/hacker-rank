package com.test1;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int[] arr = new int[T];

        int i = 0;
        while (T-- > 0) {
            int ele = sc.nextInt();
            arr[i] = ele;
            i++;
        }

        for (int j = 0; j < arr.length; j++) {
            isPrime(arr[j]);
        }
    }

    static void isPrime(int num) {
        int t = (int) Math.sqrt((double) num);
        for (int i = 2; i <= t; i++) {
            if (num % i == 0) {
                System.out.println("Not prime");
                return;
            }
        }
        System.out.println("Prime");
    }
}