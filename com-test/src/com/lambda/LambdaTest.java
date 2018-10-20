package com.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaurav Saini on 5/22/2018.
 */
class Animal {
    private boolean canFly;

    public Animal(boolean canFly) {
        this.canFly = canFly;
    }

    public boolean canFly() {
        return canFly;
    }
}

public class LambdaTest {

    static int d = 0;
    int z = 0;

    public static void main(String[] args) {
        List<Animal> list = new ArrayList<>();
        list.add(new Animal(true));
        list.add(new Animal(false));

        /*printCanFly(list, new Match() {
            @Override
            public boolean test(Animal a) {
                return a.canFly();
            }
        });*/

        printCanFly(list, a -> a.canFly());

        int a = 10;
        Match new1 = b -> {
            // a++;
            int c = a + 10;
            int e = d + 20; // can only access static from static block
            //System.out.println(z); // cannot access non-static
            return false;
        };
    }

    void newMethod() {
        int a = 10;
        Match new1 = b -> {
            // a++; // implicit final in enclosing scope
            int c = a + 10;
            int e = d + 20; // can access static
            System.out.println(z); // can access non-static
            return false;
        };
    }

    private static void printCanFly(List<Animal> list, Match m) {
        for (Animal a : list) {
            System.out.println(m.test(a));
        }
    }
}

interface Match {
    boolean test(Animal a);
}
