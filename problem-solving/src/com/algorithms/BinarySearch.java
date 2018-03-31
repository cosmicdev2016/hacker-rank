package com.algorithms;

public class BinarySearch {

    private static class BinarySearchImpl {

        protected int callBinarySearch(int[] array, int N, int searchElement, boolean isRecursive) {
            if (isRecursive) {
                return recursive(array, 0, N - 1, searchElement);
            } else {
                return iterative(array, N, searchElement);
            }
        }

        private int iterative(int[] array, int N, int searchElement) {

            int low = 0;
            int high = N - 1;

            while (low <= high) {

                //actual mid = (low+high)/2
                //changed to below to avoid int overflow for large values of low and high
                int mid = (high - low) / 2 + low;

                if (array[mid] == searchElement) {
                    return mid;
                } else if (searchElement > array[mid]) {
                    //search right sorted array
                    low = mid + 1;
                } else {
                    //search left sorted array
                    high = mid - 1;
                }
            }

            //nothing found
            return -1;
        }

        private int recursive(int[] array, int low, int high, int searchElement) {

            if (low > high) {
                return -1;
            }

            //actual mid = (low+high)/2
            //changed to below to avoid int overflow for large values of low and high
            int mid = (high - low) / 2 + low;

            if (array[mid] == searchElement) {
                return mid;
            } else if (searchElement > array[mid]) {
                //search right sorted array
                return recursive(array, mid + 1, high, searchElement);
            } else {
                //search left sorted array
                return recursive(array, low, mid - 1, searchElement);
            }
        }
    }

    private static class Problems {

        //find first or last occurrence of an element in sorted array
        protected int findFirstOrLastOccurrenceOfAnElement(int[] array, int N, int searchElement, boolean isLast) {

            int low = 0;
            int high = N - 1;

            int result = -1;

            while (low <= high) {

                //actual mid = (low+high)/2
                //changed to below to avoid int overflow for large values of low and high
                int mid = (high - low) / 2 + low;

                if (array[mid] == searchElement) {
                    result = mid;
                    /*********************************************************************/
                    if (isLast) {
                        //search right sorted array for any other occurrences (bcoz only right side would have last
                        // occurrence)
                        low = mid + 1;
                    } else {
                        //search left sorted array for any other occurrences (bcoz only left side would have first
                        // occurrence)
                        high = mid - 1;
                    }
                    /*********************************************************************/
                } else if (searchElement > array[mid]) {
                    //search right sorted array
                    low = mid + 1;
                } else {
                    //search left sorted array
                    high = mid - 1;
                }
            }

            return result;
        }

        protected int findCountOfOccurrences(int[] array, int N, int searchElement) {

            int firstOccurence = findFirstOrLastOccurrenceOfAnElement(array, N, searchElement, false);
            //if first occurrence itself does not exist, then return
            if (firstOccurence == -1) {
                return 0;
            }
            int lastOccurence = findFirstOrLastOccurrenceOfAnElement(array, N, searchElement, true);

            return lastOccurence - firstOccurence + 1;
        }

        protected int findNumOfTimesArrayRotated(int[] array, int N) {

            int low = 0;
            int high = N - 1;

            /**
             * Property of rotated array:
             *  a> if the first element < last element in the sorted array, then array was NOT rotated
             *  b> prev and next elements of the pivot are greater than pivot
             *      where, pivot is the minimum element in the array
             *      e.g. 7, 8, 1, 2, 3, 4, 5, 6  => PIVOT = 1
             */
            // look for a> property
            if (array[low] < array[high]) {
                return 0;
            }

            while (low <= high) {

                //actual mid = (low+high)/2
                //changed to below to avoid int overflow for large values of low and high
                int mid = (high - low) / 2 + low;
                //prev element to 0th index = last index (consider it as a circular array)
                int prev = (N + mid - 1) % N;
                //mext element to last index = 0th index (consider it as a circular array)
                int next = (mid + 1) % N;

                //look for b> property
                if (array[prev] > array[mid] && array[mid] < array[next]) {
                    return mid;
                } else if (array[low] < array[mid]) {
                    //search right sorted array
                    low = mid + 1;
                } else {
                    //search left sorted array
                    high = mid - 1;
                }
            }

            //nothing found
            return -1;
        }

        protected int findElementInCircularArray(int[] array, int N) {

            //nothing found
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] testArr1 = {2, 3, 5, 7, 11, 13};
        int[] testArr2 = {2, 3, 5, 7, 11, 13, 17};

        testBinarySearch(testArr1, false);
        testBinarySearch(testArr1, true);

        testBinarySearch(testArr2, false);
        testBinarySearch(testArr2, true);

        int[] testFindOccurrenceArr = {2, 2, 3, 3, 3, 3, 5, 7, 11, 11, 11, 13, 17, 17, 19};

        testFindOccurrence(testFindOccurrenceArr);

        testFindCountOfOccurrences(testFindOccurrenceArr);

        testFindArrayRotated();
    }

    private static void testFindArrayRotated() {
        Problems problem = new Problems();
        System.out.println("---------Binary Search - Find no. of times array is rotated---------");

        int[] testRotatedArr1 = {2, 3, 5, 7, 11, 13, 17};
        int[] testRotatedArr2 = {13, 17, 2, 3, 5, 7, 11};
        int[] testRotatedArr3 = {3, 5, 7, 11, 13, 17, 2};

        printCountArrayRotated(testRotatedArr1, problem);
        printCountArrayRotated(testRotatedArr2, problem);
        printCountArrayRotated(testRotatedArr3, problem);
    }

    private static void printCountArrayRotated(int[] array, Problems problem) {
        int numTimesRotated = problem.findNumOfTimesArrayRotated(array, array.length);
        System.out.println("Array is rotated : " + numTimesRotated + " times");
    }

    private static void testFindCountOfOccurrences(int[] array) {
        Problems problem = new Problems();
        System.out.println("---------Binary Search - Find count of occurrences---------");

        printCountOfOccurrence(array, problem, 3);
        printCountOfOccurrence(array, problem, 1);
        printCountOfOccurrence(array, problem, 5);
        printCountOfOccurrence(array, problem, 17);
        printCountOfOccurrence(array, problem, 21);
    }

    private static void printCountOfOccurrence(int[] array, Problems problem, int searchElement) {
        int count = problem.findCountOfOccurrences(array, array.length, searchElement);
        System.out.println("Count of occurrences for " + searchElement + " is : " + count);
    }

    private static void testFindOccurrence(int[] array) {
        Problems problem = new Problems();
        System.out.println("---------Binary Search - Find first or last occurrence---------");

        printOccurrence(array, problem, 3, false);
        printOccurrence(array, problem, 3, true);

        printOccurrence(array, problem, 5, false);
        printOccurrence(array, problem, 5, true);

        printOccurrence(array, problem, 17, false);
        printOccurrence(array, problem, 17, true);
    }

    private static void printOccurrence(int[] array, Problems problem, int searchElement, boolean isLast) {
        int location = problem.findFirstOrLastOccurrenceOfAnElement(array, array.length, searchElement, isLast);
        System.out.println((isLast ? "Last" : "First") + " occurrence of " + searchElement + " is : " + location);
    }

    private static void testBinarySearch(int[] array, boolean isRecursive) {
        BinarySearchImpl impl = new BinarySearchImpl();
        System.out.println("---------Binary Search - Using recursive: " + isRecursive + "---------");

        printLocation(array, impl, -1, isRecursive);
        printLocation(array, impl, 2, isRecursive);
        printLocation(array, impl, 7, isRecursive);
        printLocation(array, impl, 13, isRecursive);
        printLocation(array, impl, 17, isRecursive);
        printLocation(array, impl, 6, isRecursive);
        printLocation(array, impl, 9, isRecursive);
    }

    private static void printLocation(int[] array, BinarySearchImpl impl, int searchElement, boolean isRecursice) {
        int location = impl.callBinarySearch(array, array.length, searchElement, isRecursice);
        System.out.println(searchElement + " is" + ((location == -1) ? " NOT" : "") + " found" + ((location != -1) ?
                " at index : " + location : ""));
    }
}