package com.test.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Gaurav Saini on 1/11/2018.
 */

public class AnnotationProcessor implements InvocationHandler {
    private Object delegate;

    public AnnotationProcessor(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object result = null;
        try {
            Method m = this.delegate.getClass().getMethod(method.getName(), method.getParameterTypes());
            if (m.isAnnotationPresent(Testing.class)) {
                System.out.println("\tIn the annotation processor");
            }
            result = method.invoke(this.delegate, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}