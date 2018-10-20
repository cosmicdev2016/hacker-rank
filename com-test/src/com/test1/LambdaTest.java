package com.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.IntStream;

/**
 * Created by Gaurav Saini on 2/11/2018.
 */
public class LambdaTest {

    public static void main(String[] args) {

        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee red = new Employee("Red RidingHood", 35);
        Employee charming = new Employee("Prince Charming", 31);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charming);

        //sort data
        /*Collections.sort(employees, (e1, e2) -> e1.getName().compareTo(e2.getName()));
        for (Employee e : employees) {
            System.out.println(e.getName());
        }*/

        System.out.println(stringOP((s1, s2) -> s1.toUpperCase() + s2.toUpperCase(),
                employees.get(0).getName(), employees.get(1).getName()));

        //extract lambda on a separate line
        UpperAndConcat uc = (s1, s2) -> s1.toUpperCase() + s2.toUpperCase();
        System.out.println(stringOP(uc, employees.get(0).getName(), employees.get(1).getName()));

        //lambda can be split with return keyword
        UpperAndConcat uc2 = (s1, s2) -> {
            String result = s1.toUpperCase() + s2.toUpperCase();
            return result;
        };
        System.out.println(stringOP(uc2, employees.get(0).getName(), employees.get(1).getName()));

        //Lambda are treated as nested blocks
        AnotherClass anotherClass = new AnotherClass();
        String s = anotherClass.doSomething();
        System.out.println(s);


        //enhance for loop & effectively final -> no error bcoz employee reference is effectively final
        System.out.println("*******************");
        for (Employee employee : employees) {
            System.out.println(employee.getName());
            new Thread(() -> System.out.println(employee.getAge())).start();
        }

        //variable is employee is still effectively final
        System.out.println("*******************");
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            System.out.println(employee.getName());
            new Thread(() -> System.out.println(employee.getAge())).start();
        }

        /*System.out.println("*******************");
        //IntelliJ does not give error until you run the program
        Employee employee;
        for (int i = 0; i < employees.size(); i++) {
            employee = employees.get(i);
            System.out.println(employee.getName());
            new Thread(() -> System.out.println(employee.getAge())).start();
        }*/

        System.out.println("*******************");
        //Iterable.forEach
        employees.forEach((employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        }));

        //Learn about consumers & predicates
        /*System.out.println("*******************");
        System.out.println("Employees over 30");
        employees.forEach((employee -> {
            if (employee.getAge() > 30) {
                System.out.println(employee.getName());
            }
        }));
        System.out.println("Employees 30 and younger");
        employees.forEach((employee -> {
            if (employee.getAge() <= 30) {
                System.out.println(employee.getName());
            }
        }));*/
        printEmployeesByAge(employees, "Employees over 30", employee -> employee.getAge() > 30);
        printEmployeesByAge(employees, "Employees 30 and younger", employee -> employee.getAge() <= 30);

        IntPredicate greaterThan15 = i -> i > 15;
        System.out.println(greaterThan15.test(10)); //false
        int a = 20;
        System.out.println(greaterThan15.test(a + 5)); //true

        IntPredicate lessThan100 = i -> i < 100;
        System.out.println(greaterThan15.and(lessThan100).test(50)); //true
        System.out.println(greaterThan15.and(lessThan100).test(15)); //false

        //supplier - takes no args, but returns a value
        //old way
        /*Random random = new Random();
        for (int i = 0; i < 10; i++) {
            //0 to 999
            System.out.println(random.nextInt(1000));
        }*/
        //using supplier
        Random random = new Random();
        Supplier<Integer> randomSupplier = () -> random.nextInt(1000);
        for (int i = 0; i < 10; i++) {
            //0 to 999
            System.out.println(randomSupplier.get());
        }

        //Functional interfaces
        Function<Employee, String> getLastName = (employee) -> {
            String lastName = employee.getName().substring(employee.getName().indexOf(" ") + 1);
            return lastName;
        };
        //System.out.println(getLastName.apply(employees.get(1))); //Buchalka

        Function<Employee, String> getFirstName = (employee) -> {
            String lastName = employee.getName().substring(0, employee.getName().indexOf(" "));
            return lastName;
        };

        System.out.println(getName(employees.get(1), getFirstName));
        System.out.println(getName(employees.get(1), getLastName));

        //function chaining
        //1. upper case
        //2. get first Name
        Function<Employee, String> upperCase = (employee -> employee.getName().toUpperCase());
        Function<String, String> firstName = (name) -> name.substring(0, name.indexOf(" "));
        Function<Employee, String> chainFunction = upperCase.andThen(firstName);
        System.out.println("using andThen : " + chainFunction.apply(employees.get(1)));
        //reverse approach to andThen() using compose()
        Function<Employee, String> chainFunction2 = firstName.compose(upperCase);
        System.out.println("using compose : " + chainFunction2.apply(employees.get(1)));

        //concat first name and age
        BiFunction<String, Employee, String> concatAge = (name, employee) -> name.concat(" " + employee.getAge());
        String firstName2 = chainFunction.apply(employees.get(1));
        System.out.println(concatAge.apply(firstName2, employees.get(1)));

        System.out.println(isPrime(5));
        System.out.println(isPrime(6));
        System.out.println(isPrime(2));

        IntStream.range(2, 10).filter(LambdaTest::isPrime).forEach(System.out::println);
    }

    private static boolean isPrime(int num) {
        IntPredicate isDivisible = (divisor -> num % divisor == 0);
        return num > 2 &&
                IntStream.range(2, num).noneMatch(isDivisible);
    }

    private static String getName(Employee employee, Function<Employee, String> getName) {
        return getName.apply(employee);
    }

    public static String stringOP(UpperAndConcat uc, String s1, String s2) {
        return uc.execute(s1, s2);
    }

    private static void printEmployeesByAge(List<Employee> employees, String msg, Predicate<Employee> ageCondition) {
        System.out.println("*******************");
        System.out.println(msg);

        for (Employee employee : employees) {
            if (ageCondition.test(employee)) {
                System.out.println(employee.getName());
            }
        }
    }
}

class Employee {
    private String name;
    private int age;

    Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

interface UpperAndConcat {
    String execute(String s1, String s2);
}

class AnotherClass {
    public String doSomething() {
        System.out.println("another class name is : " + getClass().getSimpleName());

        /*return LambdaTest.stringOP(new UpperAndConcat() {
            @Override
            public String execute(String s1, String s2) {
                System.out.println("anonymous class name is : " + getClass().getSimpleName());
                String s = s1.toUpperCase() + s2.toUpperCase();
                return s;
            }
        }, "string1", "string2");*/

        //local variable
        int i = 0;

        //replacing above anonymous class with lambda
        UpperAndConcat uc = (s1, s2) -> {
            System.out.println("lamda class name is : " + getClass().getSimpleName());
            System.out.println("value of enclosing variable i : " + i);
            String s = s1.toUpperCase() + s2.toUpperCase();
            return s;
        };

        String s = LambdaTest.stringOP(uc, "string1", "string2");
        return s;
    }
}

