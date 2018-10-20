package com.test2;

/**
 * Created by Gaurav Saini on 2/21/2018.
 */
public abstract class StaticUtil1 {
    abstract void defaultMethod();
}

class B extends StaticUtil1 {
    @Override
    protected void defaultMethod() {

    }
}

class C extends B {
    @Override
    public void defaultMethod() {
        super.defaultMethod();
    }

    public void defaultMethod(int i) {
        super.defaultMethod();
    }
}