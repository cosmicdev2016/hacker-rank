package com.algorithms;

public class Sorting {

    private static class InsertionSort {

        protected void sortArray(int[] array) {

            for (int index = 1; index < array.length; index++) {

                int hole = index;
                int key = array[hole];
                //left side of hole is always sorted

                //move the key to a place on left side, such that left side is always sorted
                while (hole > 0 && key < array[hole - 1]) {
                    array[hole] = array[hole - 1];
                    hole--;
                }
                array[hole] = key;
            }
        }
    }

    private static class BinaryInsertionSort {

        protected void sortArray(int[] array) {

            for (int index = 1; index < array.length; index++) {

                int hole = index;
                int key = array[hole];
                //left side of hole is always sorted

                int start = getInsertionPointUsingBinarySearch(array, hole, key);

                //move the key to a place on left side, such that left side is always sorted
                while (hole > start) {
                    array[hole] = array[hole - 1];
                    hole--;
                }
                array[hole] = key;
            }
        }

        private int getInsertionPointUsingBinarySearch(int[] array, int hole, int key) {

            int low = 0;
            int high = hole - 1;
            int mid = -1;
            int start = -1;

            /**
             * Use Binary Search to find the place where key should be inserted
             * 3 cases to be handled:
             *  a> start lies before the 0th index
             *  b> start lies after the high index => hole
             *  c> start lies in between low and high
             */
            while (low <= high) {
                //actual mid = (low+high)/2
                //changed to below to avoid int overflow for large values of low and high
                mid = (high - low) / 2 + low;

                if (key < array[mid]) {
                    high = mid - 1;
                    //handle a> scenario
                    if (high == -1) {
                        start = 0;
                        break;
                    }
                } else if (key > array[mid]) {
                    low = mid + 1;
                    //handle b> scenario
                    if (low == hole) {
                        start = hole;
                        break;
                    }
                }
            }

            //handle c> scenario
            if (start == -1) {
                start = low;
            }
            return start;
        }
    }

    public static void main(String[] args) {

        int[] testArray = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] testArray2 = {11, 10, 9, -1, 4, 12, 56, -20, 6, 7, 8, 0, 3};
        int[] testArray3 = {1, 2, 3, 4, 5, 6, 7, 8};

        System.out.println("Insertion Sort...");
        testInsertionSort(testArray);
        testInsertionSort(testArray2);
        testInsertionSort(testArray3);

        System.out.println("Binary Insertion Sort...");
        testBinaryInsertionSort(testArray);
        testBinaryInsertionSort(testArray2);
        testBinaryInsertionSort(testArray3);
    }

    private static void testBinaryInsertionSort(int[] array) {
        BinaryInsertionSort sorting = new BinaryInsertionSort();
        sorting.sortArray(array);
        Utils.printArray(array);
    }

    private static void testInsertionSort(int[] array) {
        InsertionSort sorting = new InsertionSort();
        sorting.sortArray(array);
        Utils.printArray(array);
    }
}
