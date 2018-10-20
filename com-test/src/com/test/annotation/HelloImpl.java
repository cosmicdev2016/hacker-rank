package com.test.annotation;

/**
 * Created by Gaurav Saini on 1/11/2018.
 */
public class HelloImpl implements IHello {
    private static IHello proxiedImpl;
    private HelloImpl() {}

    public static IHello getProxiedInstance() {
        if (proxiedImpl == null) {
            final HelloImpl impl = new HelloImpl();
            proxiedImpl = (IHello) ProxyHelper.bind(impl, new AnnotationProcessor(impl));
        }
        return proxiedImpl;
    }

    @Override
    @Testing
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }

    @Override
    public void sayGoogBye(String name) {
        System.out.println(name+" GoodBye!");
    }
}
