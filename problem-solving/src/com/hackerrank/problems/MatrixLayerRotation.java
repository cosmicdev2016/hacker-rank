package com.hackerrank.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaurav Saini
 * <p>
 * You are given a 2D matrix of dimension (m x n) and a positive integer r. You have to rotate the matrix r times and
 * print the resultant matrix. Rotation should be in anti-clockwise direction.
 */
public class MatrixLayerRotation {
    //m x n matrix
    public static void main(String[] args) {

        int[][] arr1 = {{1, 2}};
        int[][] arr2 = {{1},
                {2},
                {3}};
        int[][] arr3 = {{1, 2, 3, 4}};
        int[][] arr4 = {{1},
                {2},
                {3},
                {4}};
        int[][] arr5 = {{1, 2, 3},
                {3, 4, 5}};
        int[][] arr6 = {{1, 2, 3},
                {3, 4, 5},
                {6, 7, 8},
                {9, 10, 11}};
        int[][] arr7 = {{1, 2},
                {3, 4},
                {6, 7},
                {9, 10}};
        int[][] arr8 = {{1, 2, 3, 21},
                {3, 4, 5, 22},
                {6, 7, 8, 23},
                {9, 10, 11, 24},
                {30, 31, 32, 33}};


        int rotationCount = 2;

        rotateMatrix(arr1, rotationCount);
        rotateMatrix(arr2, rotationCount);
        rotateMatrix(arr3, rotationCount);
        rotateMatrix(arr4, rotationCount);
        rotateMatrix(arr5, rotationCount);
        rotateMatrix(arr6, rotationCount);
        rotateMatrix(arr7, rotationCount);
        rotateMatrix(arr8, rotationCount);
    }

    private static void rotateMatrix(int[][] arr1, int rotationCount) {
        System.out.println(arr1.length + " x " + arr1[0].length);
        System.out.println("----- ORIGINAL -----");
        printArray(arr1);
        System.out.println("----- ROTATED -----");
        traverse2dArray(arr1, 0, 0, arr1.length, arr1[0].length, rotationCount);
        printArray(arr1);
    }

    private static void printArray(int[][] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i == 0 ? "{ " : "  { ");
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.print(i < arr.length - 1 ? "}, " : "} ]");
            System.out.println();
        }
    }

    private static void traverse2dArray(int[][] mainArr, final int beginR, final int beginC, final int MAX_R, final
    int MAX_C, final int rotationCount) {

        List<Integer> list = createListOfElements(mainArr, beginR, beginC, MAX_R, MAX_C);

        //System.out.println(list);
        //rotate list 'rotationCount' times
        /*for (int i = 1; i <= rotationCount; i++) {
            rotateListAntiClockWise(list);
        }*/

        //leftRotate(list, rotationCount);

        rightRotate(list, rotationCount);

        //Replace rotated list in the main array: Uses same logic as traversal
        replaceRotatedListInMainArray(mainArr, beginR, beginC, MAX_R, MAX_C, list);

        //switch to nested array: check to see if nested array exist or not
        if (MAX_R == 1 || MAX_C == 1) {
            return;
        } else {
            int diffR = Math.abs((beginR + 1) - (MAX_R - 1));
            int diffC = Math.abs((beginC + 1) - (MAX_C - 1));
            if (diffR == 0 || diffC == 0) {
                return;
            }
        }
        traverse2dArray(mainArr, beginR + 1, beginC + 1, MAX_R - 1, MAX_C - 1, rotationCount);
    }

    private static List<Integer> createListOfElements(int[][] arr, int beginR, int beginC, int MAX_R, int MAX_C) {
        int row = beginR;
        int col = beginC;
        List<Integer> list = new ArrayList<>();

        for (; row < MAX_R; row++) {
            list.add(arr[row][col]);
        }
        row--;
        col++;
        for (; col < MAX_C; col++) {
            list.add(arr[row][col]);
        }
        row--;
        col--;
        if (row != beginR || col != beginC) {
            for (; MAX_C > 1 && row >= beginR; row--) {
                list.add(arr[row][col]);
            }
        }
        row++;
        col--;
        for (; MAX_R > 1 && col > beginC; col--) {
            list.add(arr[row][col]);
        }
        return list;
    }

    private static void replaceRotatedListInMainArray(int[][] arr, final int beginR, final int beginC, final int
            MAX_R, final int MAX_C, List<Integer> list) {
        //Replace rotated list in the main array: Uses same logic as traversal
        int row = beginR;
        int col = beginC;

        int index = 0;
        for (; row < MAX_R; row++) {
            arr[row][col] = list.get(index);
            index++;
        }
        row--;
        col++;
        for (; col < MAX_C; col++) {
            arr[row][col] = list.get(index);
            index++;
        }
        row--;
        col--;
        if (row != beginR || col != beginC) {
            for (; MAX_C > 1 && row >= beginR; row--) {
                arr[row][col] = list.get(index);
                index++;
            }
        }
        row++;
        col--;
        for (; MAX_R > 1 && col > beginC; col--) {
            arr[row][col] = list.get(index);
            index++;
        }
    }

    /**
     * right rotation: expensive complexity
     */
    private static void rotateListAntiClockWise(List<Integer> list) {
        //List<String> list = new ArrayList<String>(Arrays.asList(sb.toString().split("\\s*,\\s*")));

        //store the last element
        int placeHolder = list.get(list.size() - 1);

        //rotate list anticlockwise
        int index = list.size() - 2;
        while (index >= 0) {
            list.set(index + 1, list.get(index));
            index--;
        }

        //put last element at 0th position
        list.set(0, placeHolder);
    }

    /**
     * right rotation: low complexity - O(n)
     */
    public static void rightRotate(List<Integer> list, int rotate) {
        final int length = list.size();
        if (list == null || length == 0 || rotate < 0) {
            throw new IllegalArgumentException("Illegal argument!");
        }

        if (rotate > length) {
            rotate = rotate % length;
        }

        //length of first part
        int a = length - rotate;

        reverse(list, 0, a - 1);
        reverse(list, a, length - 1);
        reverse(list, 0, length - 1);

    }

    private static void reverse(List<Integer> list, int left, int right) {
        final int length = list.size();
        if (list == null || length == 1)
            return;

        while (left < right) {
            int temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);
            left++;
            right--;
        }
    }

    private static void leftRotate(List<Integer> list, int rotate) {
        final int size = list.size();

        int i, j, k, temp;
        for (i = 0; i < gcd(rotate, size); i++) {
            /* move i-th values of blocks */
            temp = list.get(i);
            j = i;
            while (true) {
                k = j + rotate;
                if (k >= size)
                    k = k - size;
                if (k == i)
                    break;
                list.set(j, list.get(k));
                j = k;
            }
            list.set(j, temp);
        }
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
