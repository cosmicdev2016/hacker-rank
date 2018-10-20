package com.test.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Gaurav Saini on 1/11/2018.
 */
public class ProxyHelper {
    public static Object bind(Object delegate, InvocationHandler processor) {
        return Proxy.newProxyInstance(
                processor.getClass().getClassLoader(),
                delegate.getClass().getInterfaces(),
                processor
        );
    }
}
