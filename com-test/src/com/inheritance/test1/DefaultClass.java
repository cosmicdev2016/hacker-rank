package com.inheritance.test1;

//public parent, default members
public class DefaultClass {
    int y;

    static void staticTest() {
        System.out.println("static of DefaultClass");
    }

    public static void main(String[] args) {
        DefaultClass parent = new ChildofDefault();
        parent.staticTest();
    }
}

//default child of
class ChildofDefault extends DefaultClass {
    void showY() {
        System.out.println(this.y);
    }

    void showY_2() {
        DefaultClass defaultClassRef = new DefaultClass();

        System.out.println(defaultClassRef.y);
    }

    protected static void staticTest() {
        System.out.println("static of ChildofDefault");
    }
}