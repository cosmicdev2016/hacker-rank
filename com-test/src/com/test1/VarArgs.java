package com.test1;

public class VarArgs {

    public static void main(String[] args) {

        //no arguments
        new VarArgs().m1();

        //variable size argument list
        new VarArgs().m1(1, 2, 3, 4);

        //pass array
        new VarArgs().m1(new int[]{5, 6, 7, 8});

        //calls the overloaded method
        new VarArgs().m1(1, 4);

        new VarArgs().m2(new A(), new B());
        new VarArgs().m2(new B(), new A());
    }

    void m1(int... varargs) {
        //varargs behaves like an array
        System.out.println(varargs.length);
        for (int val : varargs) {
            System.out.print(" " + val);
        }
    }

    //Overloaded method: method without varargs will be invoked
    void m1(int a, int b) {
        System.out.println("\na + b = " + (a + b));
    }

    void m2(A... objects) {
        System.out.println("in m2() with A types");
    }

    //never invoked
    void m2(B... objects) {
        System.out.println("in m2() with B types");
    }

    //never invoked
    void m2(Object... objects) {
        System.out.println("in m2() with Object types");
    }
}

class A {

}

class B extends A {

}