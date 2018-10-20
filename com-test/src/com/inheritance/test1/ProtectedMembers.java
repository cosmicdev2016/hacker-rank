package com.inheritance.test1;

/**
 * Created by Gaurav Saini on 3/14/2018.
 */
public class ProtectedMembers {
    protected int x;

    void showX() {
        System.out.println(this.x);
    }
}

class Derived extends ProtectedMembers {
    void showX() {
        System.out.println(this.x);

        ProtectedMembers pm = new ProtectedMembers();
        System.out.println(pm.x);
    }
}

class Other {
    void showX() {
        ProtectedMembers pm = new ProtectedMembers();
        System.out.println(pm.x);

        Derived d = new Derived();
        System.out.println(d.x);
    }
}
