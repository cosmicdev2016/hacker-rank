package com.constructor;

/**
 * Created by Gaurav Saini on 5/8/2018.
 */
public class ConstructorTest {
    public static void main(String[] args) {
        //Scenario - 1
        Base obj = new Base(1);

        //Scenario - 2
        /*Base obj = new Child();
        System.out.println("----------");
        Base obj2 = new Child(1);*/

        //Scenario - 3
        /*new Redwood().go();*/
    }
}

/**
 * Scenario - 1
 */
class
Base {
    private Base() {
        System.out.println("calling private no-arg base constructor");
    }

    public Base(int a) {
        //super(); // one of these - super() or this() should be first
        this();
        System.out.println("calling base constructor");
        m1();
        Base.m2();
        m2();
    }

    void m1() {
    }

    static void m2() {
    }
}

/*class Child extends Base {
    public Child(int a) {
        super(1);
    }
}*/

/**
 * Scenario - 2
 */
/*class Base {
    public Base() {
        System.out.println("calling no-arg base constructor");
    }

    public Base(int a) {
        System.out.println("calling base constructor");
        m1();
        Base.m2();
        m2();
    }

    void m1() {
    }

    static void m2() {
    }
}

class Child extends Base {
    Child() {
        System.out.println("calling no-arg child constructor");
    }

    Child(int a) {
        this(getName());
        System.out.println("calling child constructor");
    }

    Child(String a) {
        this();
        System.out.println("name is " + a);
        getName();
    }

    static String getName() {
        return "Jack";
    }
}*/

/**
 * Scenario - 3
 */
/*
class Redwood extends Tree {
    void go() {
        go2(new Tree(), new Redwood());
        go2((Redwood) new Tree(), new Redwood());
    }
    void go2(Tree t1, Redwood r1) {
        System.out.println(t1 instanceof Redwood);
        Redwood r2 = (Redwood)t1;
        Tree t2 = (Tree)r1;
    }
}
class Tree { }*/
