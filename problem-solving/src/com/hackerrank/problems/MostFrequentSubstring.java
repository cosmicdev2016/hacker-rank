package com.hackerrank.problems;

import java.util.HashSet;
import java.util.Set;

public class MostFrequentSubstring {

    public static void main(String[] args) {

        String s = "ababab";
        int minLength = 2;
        int maxLength = 3;
        int maxUnique = 4;

        int maxCount = -1;
        for (int len = minLength; len <= maxLength; len++) {
            for (int inner = 0; inner <= s.length() - len; inner++) {
                String subStr = s.substring(inner, inner + len);
                if (distinctCount(subStr) <= maxUnique) {
                    //System.out.println(subStr + " - " + findOccurences(s, subStr));
                    int count = findOccurences(s, subStr);
                    if (count > maxCount) {
                        maxCount = count;
                    }
                }
            }
        }
        System.out.println(maxCount);
    }

    private static int distinctCount(String str) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }
        return set.size();
    }

    private static int findOccurences(String str, String subStr) {
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {

            lastIndex = str.indexOf(subStr, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += subStr.length();
            }
        }

        return count;
    }
}
