package com.inheritance.test2;

import com.inheritance.test1.DefaultClass;
import com.inheritance.test1.ProtectedMembers;

public class MyTest { //extends DefaultClass {

    DefaultClass defaultClassRef = new DefaultClass();

    void showY() {
        //System.out.println(defaultClassRef.y);
    }

    void showX() {
        ProtectedMembers pm = new ProtectedMembers();
        //System.out.println(pm.x);

        Derived d = new Derived();
        //System.out.println(d.x);
    }
}

class Derived extends ProtectedMembers {
    void showX() {
        System.out.println(this.x);

        ProtectedMembers pm = new ProtectedMembers();
        //System.out.println(pm.x);

        Derived d = new Derived();
        System.out.println(d.x);
    }
}