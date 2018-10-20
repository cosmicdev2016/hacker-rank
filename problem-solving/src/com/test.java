package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Gaurav Saini
 */


class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

public class test {
    public static Node insert(Node head, int data) {
        //Complete this method
        Node t = null;
        if (head == null) {
            head = new Node(data);
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            t = new Node(data);
            curr.next = t;
        }
        return head;
    }

    public static void display(Node head) {
        Node start = head;
        while (start != null) {
            System.out.print(start.data + " ");
            start = start.next;
        }
    }

    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        int rotate = 2;

        leftRotateMethod2(list, rotate);

        System.out.println(list);
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    private static void leftRotateMethod2(List<Integer> list, int rotate) {
        final int length = list.size();
        if (list == null || length == 0 || rotate < 0) {
            throw new IllegalArgumentException("Illegal argument!");
        }

        if (rotate > length) {
            rotate = rotate % length;
        }

        //length of first part
        int a = rotate;

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

    public static void main1(String args[]) {
        Scanner sc = new Scanner(System.in);
        Node head = null;
        int N = sc.nextInt();

        while (N-- > 0) {
            int ele = sc.nextInt();
            head = insert(head, ele);
        }
        display(head);
        sc.close();
    }
}
