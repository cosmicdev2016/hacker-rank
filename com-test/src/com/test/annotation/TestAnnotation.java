package com.test.annotation;

/**
 * Created by Gaurav Saini on 1/11/2018.
 */
public class TestAnnotation {
    public static void main(String[] arg) {
        IHello ihello = (IHello) HelloImpl.getProxiedInstance();

        ihello.sayHello("Jerry");
        ihello.sayGoogBye("Jerry");
    }
}
