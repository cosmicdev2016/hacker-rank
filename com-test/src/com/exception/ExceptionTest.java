package com.exception;

/**
 * Created by Gaurav Saini on 5/17/2018.
 */
public class ExceptionTest {
    public static void main(String[] args) {
    }

    void method1() throws Exception1, Exception2 {
        try {
            if (1 == 2) {
                throw new Exception1();
            } else {
                throw new Exception2();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // works in Java 7 onwards: throws expression must be subtype of exception in catch
            throw e;
        }
    }

    void method7() throws Exception {
        try {
            if (1 == 2) {
                throw new Exception1();
            } else {
                throw new Exception2();
            }
        } catch (Exception1 | Exception2 e) {
            e.printStackTrace();
            throw e;
        }
    }

    void method5() throws Exception1, Exception2 {
        try {
            if (1 == 2) {
                throw new Exception1();
            } else {
                throw new Exception2();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //e = new Exception1(); //re-assignment breaks the Java 7 feature above
            throw e;
        }
    }

    void method2() throws Exception {
        try {
            if (1 == 2) {
                throw new Exception1();
            } else {
                throw new Exception2();
            }
        } catch (Exception e) { // e is normal reference
            e.printStackTrace();
            //allowed if catch has only one exception
            e = new Exception3();
            throw e;
        }
    }

    void method3() throws Exception {
        try {
            if (1 == 2) {
                throw new Exception1();
            } else {
                throw new Exception2();
            }
        } catch (Exception1 | Exception2 e) { //e is implicitly final here
            e.printStackTrace();
            //NOT allowed if catch has more than one exception
            //e = new Exception3();
            throw e;
        }
    }

    void method4() throws Exception {
        try {
            if (1 == 2) {
                throw new Exception1();
            } else {
                throw new Exception2();
            }
        } /*catch (Exception1 | Exception3 e) { // catch expression should be disjoint
            e.printStackTrace();
            throw e;
        }*/ finally {

        }
    }
}

class Exception1 extends Exception {
}

class Exception2 extends Exception {
}

class Exception3 extends Exception1 {
}